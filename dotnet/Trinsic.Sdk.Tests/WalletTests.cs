using System;
using System.IO;
using System.Threading.Tasks;
using Google.Protobuf;
using Grpc.Net.Client;
using Newtonsoft.Json.Linq;
using Trinsic.Identity.Server.Tests;
using Trinsic.Services;
using Xunit;
using W3C.CCG.SecurityVocabulary;
using DIDComm.Messaging;
using System.Linq;
using FluentAssertions;
using Google.Protobuf.WellKnownTypes;
using Microsoft.Extensions.DependencyInjection;
using LinkedDataProofs;

namespace Trinsic.Sdk.Tests
{
    [Collection(HostFixture.CollectionDefinitionName)]
    public class WalletTests
    {
        private readonly WalletService service;
        private readonly HostFixture hostFixture;

        public WalletTests(HostFixture hostFixture)
        {
            service = new WalletService(hostFixture.Channel);
            this.hostFixture = hostFixture;
        }

        [Fact]
        public async Task CreateWalletProfile()
        {
            var profile = await service.CreateWallet();

            var homePath = Environment.GetFolderPath(Environment.SpecialFolder.UserProfile);
            Directory.CreateDirectory(Path.Combine(homePath, ".trinsic"));
            var path = Path.Combine(homePath, ".trinsic","profile.bin");
            File.WriteAllBytes(path, profile.ToByteArray());

            Assert.NotNull(profile);
        }

        [Fact]
        public async Task CanGetProviderConfiguration()
        {
            var client = new Wallet.WalletClient(hostFixture.Channel);

            var configuration = await client.GetProviderConfigurationAsync(new GetProviderConfigurationRequest());

            Assert.NotNull(configuration);
            Assert.NotNull(configuration.DidDocument);
            Assert.NotNull(configuration.KeyAgreementKeyId);
        }

        [Fact]
        public void GenerateProofWithJcs()
        {
            var capabilityDocument = new JObject
            {
                { "@context", Constants.SECURITY_CONTEXT_V2_URL },
                { "target", "urn:trinsic:wallets:noop" },
                { "proof", new JObject
                    {
                        { "proofPurpose", "capabilityInvocation" },
                        { "created", DateTimeOffset.UtcNow.ToString("s") },
                        { "capability", "urn:trinsic:wallets:noop" }
                    }
                }
            };

            var key = DIDKey.Generate(new GenerateKeyRequest { KeyType = KeyType.Ed25519 });
            var signingKey = key.Key.First(x => x.Crv == "Ed25519");

            var proofResponse = LDProofs.CreateProof(new DIDComm.Messaging.CreateProofRequest
            {
                Key = signingKey,
                Document = capabilityDocument.ToStruct(),
                Suite = LdSuite.JcsEd25519Signature2020
            });

            proofResponse.Should().NotBeNull();
            proofResponse.SignedDocument.Should().NotBeNull();
        }

        [Fact(DisplayName = "Demo: create wallet, set profile, search records, issue credential")]
        public async Task Demo_CreateWallet_SetProfile_SearchRecords()
        {
            // Create new wallet
            var profile = await service.CreateWallet();

            // Set the profile for the current service
            service.SetProfile(profile);

            // Search record responses
            var items = await service.Search();

            // Issue credential
            var unsignedDocument = new JObject
            {
                { "@context", "https://w3id.org/security/v3-unstable" },
                { "id", "https://issuer.oidp.uscis.gov/credentials/83627465" }
            };

            var issueResponse = await service.IssueCredential(unsignedDocument);

            // Insert document to personal wallet
            var itemId = await service.InsertItem(issueResponse);

            // Derive proof revaling all
            //var proof = await service.CreateProof(itemId, new JObject { { "@context", "https://w3id.org/security/v3-unstable" } });
        }
    }
}

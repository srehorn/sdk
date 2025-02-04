from functools import cached_property

from trinsic.access_management_service import AccessManagementService
from trinsic.account_service import AccountService
from trinsic.credential_service import CredentialService
from trinsic.proto.sdk.options.v1 import ServiceOptions
from trinsic.provider_service import ProviderService
from trinsic.security_providers import ITokenProvider
from trinsic.service_base import ServiceBase
from trinsic.template_service import TemplateService
from trinsic.trustregistry_service import TrustRegistryService
from trinsic.wallet_service import WalletService


class TrinsicService(ServiceBase):
    def __init__(
        self,
        *,
        server_config: ServiceOptions = None,
        token_provider: ITokenProvider = None
    ):
        super().__init__(server_config, token_provider)

    def close(self):
        # TODO - Channel management
        self.account.close()
        self.credential.close()
        self.template.close()
        self.provider.close()
        self.trust_registry.close()
        self.wallet.close()

    @cached_property
    def accessManagement(self) -> AccessManagementService:
        return AccessManagementService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def account(self) -> AccountService:
        return AccountService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def credential(self) -> CredentialService:
        return CredentialService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def template(self) -> TemplateService:
        return TemplateService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def provider(self) -> ProviderService:
        return ProviderService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def trust_registry(self) -> TrustRegistryService:
        return TrustRegistryService(
            server_config=self.service_options, token_provider=self.token_provider
        )

    @cached_property
    def wallet(self) -> WalletService:
        return WalletService(
            server_config=self.service_options, token_provider=self.token_provider
        )

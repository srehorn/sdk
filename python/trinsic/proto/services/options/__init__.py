# Generated by the protocol buffer compiler.  DO NOT EDIT!
# sources: services/options/field-options.proto
# plugin: python-betterproto
from dataclasses import dataclass

import betterproto
from betterproto.grpc.grpclib_server import ServiceBase


@dataclass(eq=False, repr=False)
class SdkTemplateOption(betterproto.Message):
    anonymous: bool = betterproto.bool_field(1)
    """
    Whether the service endpoint allows anonymous (no auth token necessary)
    authentication This is used by the `protoc-gen-trinsic-sdk` plugin for
    metadata.
    """

    ignore: bool = betterproto.bool_field(2)
    """
    Whether the SDK template generator should ignore this method. This method
    will be wrapped manually.
    """

    no_arguments: bool = betterproto.bool_field(3)
    """
    Whether the SDK template generator should generate this method without
    arguments, eg ProviderService.GetEcosystemInfo() where the request object
    is empty
    """

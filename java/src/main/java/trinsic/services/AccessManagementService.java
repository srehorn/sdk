package trinsic.services;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.InvalidProtocolBufferException;
import trinsic.okapi.DidException;
import trinsic.sdk.options.v1.Options;
import trinsic.services.provider.v1.*;

public class AccessManagementService extends ServiceBase {
  private final AccessManagementGrpc.AccessManagementFutureStub stub;

  public AccessManagementService() {
    this(null);
  }

  public AccessManagementService(Options.ServiceOptions.Builder options) {
    super(options);

    this.stub = AccessManagementGrpc.newFutureStub(this.getChannel());
  }
// BEGIN Code generated by protoc-gen-trinsic. DO NOT EDIT.
// target: C:\work\sdk\java\src\main\java\trinsic\services\AccessManagementService.java

  /** Adds a role assignment to an account */
  public ListenableFuture<AddRoleAssignmentResponse> addRoleAssignment(AddRoleAssignmentRequest request)
      throws InvalidProtocolBufferException, DidException {

	return withMetadata(stub, request).addRoleAssignment(request);
  }
  /** Removes a role assignment from the account */
  public ListenableFuture<RemoveRoleAssignmentResponse> removeRoleAssignment(RemoveRoleAssignmentRequest request)
      throws InvalidProtocolBufferException, DidException {

	return withMetadata(stub, request).removeRoleAssignment(request);
  }
  /** List the role assignments for the given account */
  public ListenableFuture<ListRoleAssignmentsResponse> listRoleAssignments(ListRoleAssignmentsRequest request)
      throws InvalidProtocolBufferException, DidException {

	return withMetadata(stub, request).listRoleAssignments(request);
  }
// END Code generated by protoc-gen-trinsic. DO NOT EDIT.
}

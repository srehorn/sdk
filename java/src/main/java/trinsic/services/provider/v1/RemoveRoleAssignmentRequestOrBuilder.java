// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: services/provider/v1/access-management.proto

package trinsic.services.provider.v1;

public interface RemoveRoleAssignmentRequestOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:services.provider.v1.RemoveRoleAssignmentRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * Role to unassign
   * </pre>
   *
   * <code>string role = 1;</code>
   *
   * @return The role.
   */
  java.lang.String getRole();
  /**
   *
   *
   * <pre>
   * Role to unassign
   * </pre>
   *
   * <code>string role = 1;</code>
   *
   * @return The bytes for role.
   */
  com.google.protobuf.ByteString getRoleBytes();

  /**
   *
   *
   * <pre>
   * Email address of account to unassign role from.
   * Mutually exclusive with `walletId`.
   * </pre>
   *
   * <code>string email = 2;</code>
   *
   * @return Whether the email field is set.
   */
  boolean hasEmail();
  /**
   *
   *
   * <pre>
   * Email address of account to unassign role from.
   * Mutually exclusive with `walletId`.
   * </pre>
   *
   * <code>string email = 2;</code>
   *
   * @return The email.
   */
  java.lang.String getEmail();
  /**
   *
   *
   * <pre>
   * Email address of account to unassign role from.
   * Mutually exclusive with `walletId`.
   * </pre>
   *
   * <code>string email = 2;</code>
   *
   * @return The bytes for email.
   */
  com.google.protobuf.ByteString getEmailBytes();

  /**
   *
   *
   * <pre>
   * Wallet ID of account to unassign role from.
   * Mutually exclusive with `email`.
   * </pre>
   *
   * <code>string wallet_id = 3;</code>
   *
   * @return Whether the walletId field is set.
   */
  boolean hasWalletId();
  /**
   *
   *
   * <pre>
   * Wallet ID of account to unassign role from.
   * Mutually exclusive with `email`.
   * </pre>
   *
   * <code>string wallet_id = 3;</code>
   *
   * @return The walletId.
   */
  java.lang.String getWalletId();
  /**
   *
   *
   * <pre>
   * Wallet ID of account to unassign role from.
   * Mutually exclusive with `email`.
   * </pre>
   *
   * <code>string wallet_id = 3;</code>
   *
   * @return The bytes for walletId.
   */
  com.google.protobuf.ByteString getWalletIdBytes();

  public trinsic.services.provider.v1.RemoveRoleAssignmentRequest.AccountCase getAccountCase();
}

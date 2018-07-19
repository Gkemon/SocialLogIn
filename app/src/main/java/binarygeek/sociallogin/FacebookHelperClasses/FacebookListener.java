package binarygeek.sociallogin.FacebookHelperClasses;

public interface FacebookListener {
  void onFbSignInFail(String errorMessage);

  void onFbSignInSuccess(String authToken, String userId, String email, String name);

  void onFBSignOut();

}

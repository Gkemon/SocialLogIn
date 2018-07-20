package binarygeek.sociallogin.GoogleHelperClasses;

public interface GoogleListener {
  void onGoogleAuthSignIn(String authToken, String userId, String email, String name);

  void onGoogleAuthSignInFailed(String errorMessage);

  void onGoogleAuthSignOut();
}

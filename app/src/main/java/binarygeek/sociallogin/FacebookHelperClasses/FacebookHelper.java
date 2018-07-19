package binarygeek.sociallogin.FacebookHelperClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookHelper {
  private FacebookListener mListener;
  private CallbackManager mCallBackManager;
  private String id;
  private String name;
  private String email;
  private String gender;
  private String birthday;

  public FacebookHelper(@NonNull FacebookListener facebookListener) {
    mListener = facebookListener;
    mCallBackManager = CallbackManager.Factory.create();
    FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(final LoginResult loginResult) {

        System.out.println("onSuccess");
        GraphRequest request = GraphRequest.newMeRequest
                (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                {
                  @Override
                  public void onCompleted(JSONObject object, GraphResponse response)
                  {
                    // Application code
                    Log.v("LoginActivity", response.toString());
                    //System.out.println("Check: " + response.toString());
                    try
                    {

                      id = object.getString("id");
                      name = object.getString("name");
                      email = object.getString("email");
                      gender = object.getString("gender");
                      birthday = object.getString("birthday");
                      System.out.println("HHHHH"+id + ", " + name + ", " + email + ", " + gender + ", " + birthday);

                    }
                    catch (JSONException e)
                    {
                      e.printStackTrace();
                    }

                  }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();
        mListener.onFbSignInSuccess(loginResult.getAccessToken().getToken(),
                id,email,name);


      }

      @Override public void onCancel() {
        mListener.onFbSignInFail("User cancelled operation");
      }

      @Override public void onError(FacebookException e) {
        mListener.onFbSignInFail(e.getMessage());
      }
    };
    LoginManager.getInstance().registerCallback(mCallBackManager, mCallBack);
  }

  @NonNull
  @CheckResult
  public CallbackManager getCallbackManager() {
    return mCallBackManager;
  }

  public void performSignIn(Activity activity) {
    LoginManager.getInstance()
        .logInWithReadPermissions(activity,
            Arrays.asList("public_profile", "user_friends", "email"));
  }

  public void performSignIn(Fragment fragment) {
    LoginManager.getInstance()
        .logInWithReadPermissions(fragment,
            Arrays.asList("public_profile", "user_friends", "email"));
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    mCallBackManager.onActivityResult(requestCode, resultCode, data);
  }

  public void performSignOut() {
    LoginManager.getInstance().logOut();
    mListener.onFBSignOut();
  }

}

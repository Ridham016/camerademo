package cordova.plugin.camerademo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.provider.MediaStore;
import android.app.Activity;
import android.net.Uri;




/**
 * This class echoes a string called from JavaScript.
 */
public class camerademo extends CordovaPlugin {

    private static final int CAMERA_REQUEST = 1;
    private CallbackContext callbackContext;
    

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("openCamera")) {
            openCamera(callbackContext);
            return true;
        }
        return false;
    }

    private void openCamera(CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cordova.startActivityForResult(this, intent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri imageUri = intent.getData();
            callbackContext.success(imageUri.toString());
        } else {
            callbackContext.error("Failed to capture image");
        }
    }
}

var exec = require('cordova/exec');

var YourPluginName = {
    openCamera: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'YourPluginName', 'openCamera', []);
    }
};

module.exports = YourPluginName;
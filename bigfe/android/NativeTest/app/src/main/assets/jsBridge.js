(() => {
  window.jsBridge = {
    callbacks: {},
    genCallbackId: function () {
      return 'cb_' + Date.now() + '_' + Math.random().toString(36).substring(2, 9);
    },
    callNative(action, params = {}, callback) {
      const callbackId = callback ? this.genCallbackId() : '';
      if (callback) {
        this.callbacks[callbackId] = callback;
      }

      try {
        window.NativeBridge.callNative(action, JSON.stringify(params), callbackId);
      } catch(err) {
        console.error('[callNative] error: ', err);
      }
    },
    onNativeCall(action, params = {}) {
      switch(action) {
        case 'alertMessage':
          alert('Naitve Msg: ' +  params.message);
          break;
      }
    },
    // native call back
    onNativeCallback(callbackId, result, isError) {
      const callback = this.callbacks[callbackId]
      if (callback) {
        if  (isError) {

          callback(new Error(result), null);
        } else {
          callback(null, result);
        }

        delete this.callbacks[callbackId];
      }
    }
  }
})()
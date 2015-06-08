package com.Cadient.QMobile.controller.error;

public interface ErrorHandler {
    /**
     * handle exception event
     *
     * @param fail
     */
    public void handleFail(Object fail);

    /**
     * handle server error event
     *
     * @param communicationError
     */
    public void handleError(Object communicationError);

    /**
     * show or hide loading dialog
     *
     * @param show
     */
    public void manageLoadDialog(boolean show);

    /**
     * show or hide loading dialog with some text
     *
     * @param show
     * @param message
     */
    public void manageLoadDialog(boolean show, String message);

//    /**
//     * show error dialog with {@message}
//     *
//     * @param message
//     * @param listener
//     */
//    public void showDialog(String message, Dialog.OnClickListener listener);

    /**
     * show error dialog with {@message}
     *
     * @param message
     */
    public void showDialog(String message);

    /**
     * hide error dialog
     */
    public void hideDialog();


    public void addListener(ErrorHandlerListener errorHandlerListener);

    public void removeListener(ErrorHandlerListener errorHandlerListener);
}

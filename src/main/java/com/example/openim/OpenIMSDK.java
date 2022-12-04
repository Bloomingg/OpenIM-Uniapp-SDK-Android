//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.openim;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

import java.nio.Buffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import open_im_sdk.Open_im_sdk;
import open_im_sdk_callback.OnAdvancedMsgListener;
import open_im_sdk_callback.OnBatchMsgListener;
import open_im_sdk_callback.OnConnListener;
import open_im_sdk_callback.OnConversationListener;
import open_im_sdk_callback.OnFriendshipListener;
import open_im_sdk_callback.OnGroupListener;
import open_im_sdk_callback.OnOrganizationListener;
import open_im_sdk_callback.OnSignalingListener;
import open_im_sdk_callback.OnUserListener;
import open_im_sdk_callback.OnWorkMomentsListener;
import open_im_sdk_callback.SendMsgCallBack;

public class OpenIMSDK extends UniModule {
    public OpenIMSDK() {
    }

    @UniJSMethod(
            uiThread = false
    )
    public Boolean initSDK(String operationID, JSONObject options) {
        OnConnListener connListener = new OnConnListener() {
            public void onConnectFailed(int i, String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", i);
                params.put("errMsg", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onConnectFailed", params);
            }

            public void onConnectSuccess() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onConnectSuccess", params);
            }

            public void onConnecting() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onConnecting", params);
            }

            public void onKickedOffline() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onKickedOffline", params);
            }

            public void onUserTokenExpired() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onUserTokenExpired", params);
            }
        };
        String configStr = options.toJSONString();
        Log.e("InitSDK-", configStr);
        Boolean blo = Open_im_sdk.initSDK(connListener, operationID, configStr);
        setSignalingListener();
        setOrganizationListener();
        setWorkMomentsListener();
        return blo;
    }

    @UniJSMethod(
            uiThread = false
    )
    public  void setOrganizationListener() {
        Open_im_sdk.setOrganizationListener(new OnOrganizationListener() {
            public void onOrganizationUpdated() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onOrganizationUpdated", params);
            }
        });
    }

    @UniJSMethod(
            uiThread = false
    )
    public  void setSignalingListener() {
        Open_im_sdk.setSignalingListener(new OnSignalingListener() {

            @Override
            public void onHangUp(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onHangUp", params);
            }

            @Override
            public void onInvitationCancelled(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInvitationCancelled", params);
            }

            @Override
            public void onInvitationTimeout(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInvitationTimeout", params);
            }

            @Override
            public void onInviteeAccepted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInviteeAccepted", params);
            }

            @Override
            public void onInviteeAcceptedByOtherDevice(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInviteeAcceptedByOtherDevice", params);
            }

            @Override
            public void onInviteeRejected(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInviteeRejected", params);
            }

            @Override
            public void onInviteeRejectedByOtherDevice(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onInviteeRejectedByOtherDevice", params);
            }

            @Override
            public void onReceiveNewInvitation(String s) {
                Map<String, Object> params = new HashMap();
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onReceiveNewInvitation", params);
            }

            @Override
            public void onRoomParticipantConnected(String s) {

            }

            @Override
            public void onRoomParticipantDisconnected(String s) {

            }
        });
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setUserListener() {
        OnUserListener userListener = new OnUserListener() {
            public void onSelfInfoUpdated(String s) {
                Map<String, Object> params = new HashMap();
                params.put("userInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onSelfInfoUpdated", params);
            }
        };
        Open_im_sdk.setUserListener(userListener);
    }

@UniJSMethod(
            uiThread = false
    )
    public void setWorkMomentsListener() {
        Open_im_sdk.setWorkMomentsListener(new OnWorkMomentsListener() {
            @Override
            public void onRecvNewNotification() {
                Map<String, Object> params = new HashMap();
                params.put("data", "onRecvNewNotification");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvNewNotification", params);
            }
        });
    }

    @UniJSMethod(
            uiThread = false
    )
    public void login(String operationID, String uid, String token, UniJSCallback callback) {
        Open_im_sdk.login(new BaseImpl(callback), operationID, uid, token);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void wakeUp(String operationID, UniJSCallback callback) {
        Open_im_sdk.wakeUp(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void logout(String operationID, UniJSCallback callback) {
        Open_im_sdk.logout(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public int getLoginStatus() {
        int status = Open_im_sdk.getLoginStatus();
        return status;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String getLoginUser() {
        String uid = Open_im_sdk.getLoginUser();
        return uid;
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getUsersInfo(String operationID, List uidList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(uidList);
        Open_im_sdk.getUsersInfo(new BaseImpl(callback), operationID, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setSelfInfo(String operationID, JSONObject userInfo, UniJSCallback callback) {
        String infoStr = userInfo.toJSONString();
        Open_im_sdk.setSelfInfo(new BaseImpl(callback), operationID, infoStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getSelfUserInfo(String operationID, UniJSCallback callback) {
        Open_im_sdk.getSelfUserInfo(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setAdvancedMsgListener() {
        OnAdvancedMsgListener advancedMsgListener = new OnAdvancedMsgListener() {
            @Override
            public void onNewRecvMessageRevoked(String s) {
                Map<String, Object> params = new HashMap();
                params.put("msgRevoked", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onNewRecvMessageRevoked", params);
            }

            public void onRecvC2CReadReceipt(String s) {
                Map<String, Object> params = new HashMap();
                params.put("msgReceiptList", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvC2CReadReceipt", params);
            }

            public void onRecvGroupReadReceipt(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupMsgReceiptList", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvGroupReadReceipt", params);
            }

            public void onRecvMessageRevoked(String s) {
                Map<String, Object> params = new HashMap();
                params.put("msgId", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvMessageRevoked", params);
            }

            public void onRecvNewMessage(String s) {
                Map<String, Object> params = new HashMap();
                params.put("message", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvNewMessage", params);
            }
        };
        Open_im_sdk.setAdvancedMsgListener(advancedMsgListener);
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createTextMessage(String OperationID, String textMsg) {
        String res = Open_im_sdk.createTextMessage(OperationID, textMsg);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createTextAtMessage(String OperationID, String textMsg, List atList, List atUserInfoList, String message) {
        String listStr = JSON.toJSONString(atList);
        String atUserInfoListStr = JSON.toJSONString(atUserInfoList);
        String res = Open_im_sdk.createTextAtMessage(OperationID, textMsg, listStr, atUserInfoListStr, message);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createImageMessage(String OperationID, String imagePath) {
        String res = Open_im_sdk.createImageMessage(OperationID, imagePath);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createImageMessageFromFullPath(String OperationID, String imagePath) {
        String res = Open_im_sdk.createImageMessageFromFullPath(OperationID, imagePath);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createImageMessageByURL(String OperationID, JSONObject sourceImageInfo, JSONObject bigImageInfo, JSONObject snapImageInfo) {
        String jsonStr1 = sourceImageInfo.toJSONString();
        String jsonStr2 = bigImageInfo.toJSONString();
        String jsonStr3 = snapImageInfo.toJSONString();
        String res = Open_im_sdk.createImageMessageByURL(OperationID, jsonStr1, jsonStr2, jsonStr3);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createSoundMessage(String OperationID, String soundPath, Integer duration) {
        String res = Open_im_sdk.createSoundMessage(OperationID, soundPath, (long)duration);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createSoundMessageFromFullPath(String OperationID, String soundPath, Integer duration) {
        String res = Open_im_sdk.createSoundMessageFromFullPath(OperationID, soundPath, (long)duration);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createSoundMessageByURL(String OperationID, JSONObject soundInfo) {
        String jsonStr = soundInfo.toJSONString();
        String res = Open_im_sdk.createSoundMessageByURL(OperationID, jsonStr);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createVideoMessage(String OperationID, String videoPath, String videoType, Integer duration, String snapshotPath) {
        String res = Open_im_sdk.createVideoMessage(OperationID, videoPath, videoType, (long)duration, snapshotPath);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createVideoMessageFromFullPath(String OperationID, String videoPath, String videoType, Integer duration, String snapshotPath) {
        String res = Open_im_sdk.createVideoMessageFromFullPath(OperationID, videoPath, videoType, (long)duration, snapshotPath);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createVideoMessageByURL(String OperationID, JSONObject videoInfo) {
        String jsonStr = videoInfo.toJSONString();
        String res = Open_im_sdk.createVideoMessageByURL(OperationID, jsonStr);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createFileMessage(String OperationID, String filePath, String fileName) {
        String res = Open_im_sdk.createFileMessage(OperationID, filePath, fileName);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createFileMessageFromFullPath(String OperationID, String filePath, String fileName) {
        String res = Open_im_sdk.createFileMessageFromFullPath(OperationID, filePath, fileName);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createFileMessageByURL(String OperationID, JSONObject fileInfo) {
        String jsonStr = fileInfo.toJSONString();
        String res = Open_im_sdk.createFileMessageByURL(OperationID, jsonStr);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createMergerMessage(String operationID, List messageList, String title, List summaryList) {
        String msgListStr = JSON.toJSONString(messageList);
        String sumStr = JSON.toJSONString(summaryList);
        String res = Open_im_sdk.createMergerMessage(operationID, msgListStr, title, sumStr);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createForwardMessage(String operationID, String message) {
        String res = Open_im_sdk.createForwardMessage(operationID, message);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createFaceMessage(String operationID,Integer idx, String customStr) {
        String res = Open_im_sdk.createFaceMessage(operationID, idx,customStr);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createLocationMessage(String OperationID, String desc, double longitude, double latitude) {
        String res = Open_im_sdk.createLocationMessage(OperationID, desc, longitude, latitude);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createCustomMessage(String operationID, JSONObject data, JSONObject expand, String desc) {
        String dataStr = data.toJSONString();
        String expandStr = expand.toJSONString();
        String res = Open_im_sdk.createCustomMessage(operationID, dataStr, expandStr, desc);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createQuoteMessage(String OperationID, String text, String msg) {
        String res = Open_im_sdk.createQuoteMessage(OperationID, text, msg);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createAdvancedQuoteMessage(String OperationID, String text, String msg,JSONArray messageEntityList) {
        String res = Open_im_sdk.createAdvancedQuoteMessage(OperationID, text, msg,messageEntityList.toJSONString());
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public String createCardMessage(String operationID, JSONObject msgObj) {
        String msg = msgObj.toJSONString();
        String res = Open_im_sdk.createCardMessage(operationID, msg);
        return res;
    }

    @UniJSMethod(
            uiThread = false
    )
    public void sendMessage(String operationID, String message, String receiver, String groupID, JSONObject offlinePushInfo) {
        SendMsgCallBack sendMsgCallBack = new SendMsgCallBack() {
            public void onError(int i, String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", i);
                params.put("errMsg", s);
                params.put("message", message);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageFailed", params);
            }

            public void onProgress(long l) {
                Map<String, Object> params = new HashMap();
                params.put("progress", l);
                params.put("message", message);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageProgress", params);
            }

            public void onSuccess(String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                params.put("message", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageSuccess", params);
            }
        };
        String offlinePushInfoStr = offlinePushInfo.toJSONString();
        Open_im_sdk.sendMessage(sendMsgCallBack, operationID, message, receiver, groupID, offlinePushInfoStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void sendMessageNotOss(String operationID, String message, String receiver, String groupID, JSONObject offlinePushInfo) {
        SendMsgCallBack sendMsgNotOssCallBack = new SendMsgCallBack() {
            public void onError(int i, String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", i);
                params.put("errMsg", s);
                params.put("data", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageFailed", params);
            }

            public void onProgress(long l) {
                Map<String, Object> params = new HashMap();
                params.put("progress", l);
                params.put("message", message);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageProgress", params);
            }

            public void onSuccess(String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageSuccess", params);
            }
        };
        String offlinePushInfoStr = offlinePushInfo.toJSONString();
        Open_im_sdk.sendMessageNotOss(sendMsgNotOssCallBack, operationID, message, receiver, groupID, offlinePushInfoStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void sendMessageByBuffer(String operationID, String message, String receiver, String groupID, JSONObject offlinePushInfo, Buffer fileBuffer,Buffer snpBuffer) {
        SendMsgCallBack sendMsgNotOssCallBack = new SendMsgCallBack() {
            public void onError(int i, String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", i);
                params.put("errMsg", s);
                params.put("data", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageFailed", params);
            }

            public void onProgress(long l) {
                Map<String, Object> params = new HashMap();
                params.put("progress", l);
                params.put("message", message);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageProgress", params);
            }

            public void onSuccess(String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("sendMessageSuccess", params);
            }
        };
        String offlinePushInfoStr = offlinePushInfo.toJSONString();
        Open_im_sdk.sendMessageByBuffer(sendMsgNotOssCallBack, operationID, message, receiver, groupID, offlinePushInfoStr,fileBuffer,snpBuffer);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getHistoryMessageList(String operationID, JSONObject options, UniJSCallback callback) {
        String optionStr = options.toJSONString();
        Open_im_sdk.getHistoryMessageList(new BaseImpl(callback), operationID, optionStr);
    }

@UniJSMethod(
            uiThread = false
    )
    public void getHistoryMessageListReverse(String operationID, JSONObject options, UniJSCallback callback) {
        String optionStr = options.toJSONString();
        Open_im_sdk.getHistoryMessageListReverse(new BaseImpl(callback), operationID, optionStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void revokeMessage(String operationID, JSONObject msg, UniJSCallback callback) {
        String message = JSON.toJSONString(msg);
        Open_im_sdk.revokeMessage(new BaseImpl(callback), operationID, message);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void markC2CMessageAsRead(String operationID, String uid, List msgIDs, UniJSCallback callback) {
        String idsStr = JSON.toJSONString(msgIDs);
        Open_im_sdk.markC2CMessageAsRead(new BaseImpl(callback), operationID, uid, idsStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void markGroupMessageAsRead(String operationID, String groupID, List msgIDs, UniJSCallback callback) {
        String idsStr = JSON.toJSONString(msgIDs);
        Open_im_sdk.markGroupMessageAsRead(new BaseImpl(callback), operationID, groupID, idsStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void typingStatusUpdate(String operationID, String uid, String typing, UniJSCallback callback) {
        Open_im_sdk.typingStatusUpdate(new BaseImpl(callback), operationID, uid, typing);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteMessageFromLocalStorage(String operationID, JSONObject msg, UniJSCallback callback) {
        String message = msg.toJSONString();
        Open_im_sdk.deleteMessageFromLocalStorage(new BaseImpl(callback), operationID, message);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteMessageFromLocalAndSvr(String operationID, JSONObject msg, UniJSCallback callback) {
        String message = msg.toJSONString();
        Open_im_sdk.deleteMessageFromLocalAndSvr(new BaseImpl(callback), operationID, message);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteAllMsgFromLocalAndSvr(String operationID, UniJSCallback callback) {
        Open_im_sdk.deleteAllMsgFromLocalAndSvr(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteAllMsgFromLocal(String operationID, UniJSCallback callback) {
        Open_im_sdk.deleteAllMsgFromLocal(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void insertSingleMessageToLocalStorage(String operationID, String message, String userID, String sender, UniJSCallback callback) {
        Open_im_sdk.insertSingleMessageToLocalStorage(new BaseImpl(callback), operationID, message, userID, sender);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void insertGroupMessageToLocalStorage(String operationID, String message, String groupID, String sendID, UniJSCallback callback) {
        Open_im_sdk.insertGroupMessageToLocalStorage(new BaseImpl(callback), operationID, message, groupID, sendID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void clearC2CHistoryMessage(String operationID, String userID, UniJSCallback callback) {
        Open_im_sdk.clearC2CHistoryMessage(new BaseImpl(callback), operationID, userID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void clearGroupHistoryMessage(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.clearGroupHistoryMessage(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void clearC2CHistoryMessageFromLocalAndSvr(String operationID, String userID, UniJSCallback callback) {
        Open_im_sdk.clearC2CHistoryMessageFromLocalAndSvr(new BaseImpl(callback), operationID, userID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void clearGroupHistoryMessageFromLocalAndSvr(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.clearGroupHistoryMessageFromLocalAndSvr(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void searchLocalMessages(String operationID, JSONObject searchParam, UniJSCallback callback) {
        String searchParamStr = JSON.toJSONString(searchParam);
        Open_im_sdk.searchLocalMessages(new BaseImpl(callback), operationID, searchParamStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setConversationListener() {
        OnConversationListener conversationListener = new OnConversationListener() {
            public void onConversationChanged(String s) {
                Map<String, Object> params = new HashMap();
                params.put("conversationList", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onConversationChanged", params);
            }

            public void onNewConversation(String s) {
                Map<String, Object> params = new HashMap();
                params.put("conversationList", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onNewConversation", params);
            }

            public void onSyncServerFailed() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onSyncServerFailed", params);
            }

            public void onSyncServerFinish() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onSyncServerFinish", params);
            }

            public void onSyncServerStart() {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onSyncServerStart", params);
            }

            public void onTotalUnreadMessageCountChanged(int i) {
                Map<String, Object> params = new HashMap();
                params.put("totalUnreadCount", i);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onTotalUnreadMessageCountChanged", params);
            }
        };
        Open_im_sdk.setConversationListener(conversationListener);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getAllConversationList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getAllConversationList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getConversationListSplit(String operationID, long offset, long count, UniJSCallback callback) {
        Open_im_sdk.getConversationListSplit(new BaseImpl(callback), operationID, offset, count);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getOneConversation(String operationID, Integer session, String sourceID, UniJSCallback callback) {
        Open_im_sdk.getOneConversation(new BaseImpl(callback), operationID, (long)session, sourceID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getMultipleConversation(String operationID, List conversationID, UniJSCallback callback) {
        String conversationIDList = JSON.toJSONString(conversationID);
        Open_im_sdk.getMultipleConversation(new BaseImpl(callback), operationID, conversationIDList);
    }

    @UniJSMethod(
            uiThread = false
    )
    public String getConversationIDBySessionType(String sourceId, long sessionType) {
        String id = Open_im_sdk.getConversationIDBySessionType(sourceId, sessionType);
        return id;
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteConversation(String operationID, String conversationID, UniJSCallback callback) {
        Open_im_sdk.deleteConversation(new BaseImpl(callback), operationID, conversationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteConversationFromLocalAndSvr(String operationID, String conversationID, UniJSCallback callback) {
        Open_im_sdk.deleteConversationFromLocalAndSvr(new BaseImpl(callback), operationID, conversationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteAllConversationFromLocal(String operationID, UniJSCallback callback) {
        Open_im_sdk.deleteAllConversationFromLocal(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setConversationDraft(String operationID, String conversationID, String draftText, UniJSCallback callback) {
        Open_im_sdk.setConversationDraft(new BaseImpl(callback), operationID, conversationID, draftText);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void pinConversation(String operationID, String conversationID, Boolean isPinned, UniJSCallback callback) {
        Open_im_sdk.pinConversation(new BaseImpl(callback), operationID, conversationID, isPinned);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getTotalUnreadMsgCount(String operationID, UniJSCallback callback) {
        Open_im_sdk.getTotalUnreadMsgCount(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void markGroupMessageHasRead(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.markGroupMessageHasRead(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void markConversationHasRead(String operationID, String conversationID, UniJSCallback callback) {
        String emptyArrStr = "[]";
        Open_im_sdk.markMessageAsReadByConID(new BaseImpl(callback),operationID,conversationID,emptyArrStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void markMessageAsReadByConID(String operationID, String conversationID, List msgIDList,UniJSCallback callback) {
        String emptyArrStr = JSONObject.toJSONString(msgIDList);
        Open_im_sdk.markMessageAsReadByConID(new BaseImpl(callback),operationID,conversationID,emptyArrStr);
    }


    @UniJSMethod(
            uiThread = false
    )
    public void setOneConversationPrivateChat(String operationID, String conversationID, Boolean isPrivate, UniJSCallback callback) {
        Open_im_sdk.setOneConversationPrivateChat(new BaseImpl(callback), operationID, conversationID, isPrivate);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setOneConversationRecvMessageOpt(String operationID, String conversationID, long status, UniJSCallback callback) {
        Open_im_sdk.setOneConversationRecvMessageOpt(new BaseImpl(callback), operationID, conversationID, status);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setConversationRecvMessageOpt(String operationID, List<String> conversationIDs, long status, UniJSCallback callback) {
        Open_im_sdk.setConversationRecvMessageOpt(new BaseImpl(callback), operationID, JSON.toJSONString(conversationIDs), status);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getConversationRecvMessageOpt(String operationID, List<String> conversationIDs, UniJSCallback callback) {
        Open_im_sdk.getConversationRecvMessageOpt(new BaseImpl(callback), operationID, JSON.toJSONString(conversationIDs));
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setFriendListener() {
        OnFriendshipListener friendshipListener = new OnFriendshipListener() {
            public void onBlackAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("blackInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onBlackAdded", params);
            }

            public void onBlackDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("blackInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onBlackDeleted", params);
            }

            public void onFriendAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendAdded", params);
            }

            public void onFriendApplicationAccepted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendApplicationAccepted", params);
            }

            public void onFriendApplicationAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendApplicationAdded", params);
            }

            public void onFriendApplicationDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendApplicationDeleted", params);
            }

            public void onFriendApplicationRejected(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendApplicationRejected", params);
            }

            public void onFriendDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendDeleted", params);
            }

            public void onFriendInfoChanged(String s) {
                Map<String, Object> params = new HashMap();
                params.put("friendInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onFriendInfoChanged", params);
            }
        };
        Open_im_sdk.setFriendListener(friendshipListener);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getDesignatedFriendsInfo(String operationID, List uidList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(uidList);
        Open_im_sdk.getDesignatedFriendsInfo(new BaseImpl(callback), operationID, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void addFriend(String operationID, JSONObject paramsReq, UniJSCallback callback) {
        String parStr = paramsReq.toJSONString();
        Open_im_sdk.addFriend(new BaseImpl(callback), operationID, parStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getRecvFriendApplicationList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getRecvFriendApplicationList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getSendFriendApplicationList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getSendFriendApplicationList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getFriendList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getFriendList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setFriendRemark(String operationID, JSONObject info, UniJSCallback callback) {
        String infoStr = info.toJSONString();
        Open_im_sdk.setFriendRemark(new BaseImpl(callback), operationID, infoStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void addBlack(String operationID, String uid, UniJSCallback callback) {
        Open_im_sdk.addBlack(new BaseImpl(callback), operationID, uid);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getBlackList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getBlackList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void removeBlack(String operationID, String uid, UniJSCallback callback) {
        Open_im_sdk.removeBlack(new BaseImpl(callback), operationID, uid);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void checkFriend(String operationID, List uidList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(uidList);
        Open_im_sdk.checkFriend(new BaseImpl(callback), operationID, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void acceptFriendApplication(String operationID, JSONObject uid, UniJSCallback callback) {
        String userIDHandleMsg = JSON.toJSONString(uid);
        Open_im_sdk.acceptFriendApplication(new BaseImpl(callback), operationID, userIDHandleMsg);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void refuseFriendApplication(String operationID, JSONObject uid, UniJSCallback callback) {
        String userIDHandleMsg = JSON.toJSONString(uid);
        Open_im_sdk.refuseFriendApplication(new BaseImpl(callback), operationID, userIDHandleMsg);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void deleteFriend(String operationID, String deleteUid, UniJSCallback callback) {
        Open_im_sdk.deleteFriend(new BaseImpl(callback), operationID, deleteUid);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void searchFriends(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.searchFriends(new BaseImpl(callback), operationID, options.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupListener() {
        OnGroupListener groupListener = new OnGroupListener() {
            public void onGroupApplicationAccepted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupApplicationAccepted", params);
            }

            public void onGroupApplicationAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupApplicationAdded", params);
            }

            public void onGroupApplicationDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupApplicationDeleted", params);
            }

            public void onGroupApplicationRejected(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupApplication", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupApplicationRejected", params);
            }

            public void onGroupInfoChanged(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupInfoChanged", params);
            }

            public void onGroupMemberAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupMemberInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupMemberAdded", params);
            }

            public void onGroupMemberDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupMemberInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupMemberDeleted", params);
            }

            public void onGroupMemberInfoChanged(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupMemberInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onGroupMemberInfoChanged", params);
            }

            public void onJoinedGroupAdded(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onJoinedGroupAdded", params);
            }

            public void onJoinedGroupDeleted(String s) {
                Map<String, Object> params = new HashMap();
                params.put("groupInfo", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onJoinedGroupDeleted", params);
            }
        };
        Open_im_sdk.setGroupListener(groupListener);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void inviteUserToGroup(String operationID, String groupID, String reason, List userList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(userList);
        Open_im_sdk.inviteUserToGroup(new BaseImpl(callback), operationID, groupID, reason, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void kickGroupMember(String operationID, String groupID, String reason, List userList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(userList);
        Open_im_sdk.kickGroupMember(new BaseImpl(callback), operationID, groupID, reason, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getGroupMembersInfo(String operationID, String groupID, List userList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(userList);
        Open_im_sdk.getGroupMembersInfo(new BaseImpl(callback), operationID, groupID, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getGroupMemberList(String operationID, String groupID, Integer filter, Integer offset, Integer next, UniJSCallback callback) {
        Open_im_sdk.getGroupMemberList(new BaseImpl(callback), operationID, groupID, filter, offset, next);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getGroupMemberListByJoinTimeFilter(String operationID, String groupID, Integer offset, Integer count,long joinTimeBegin,long joinTimeEnd,List filterUserIDList, UniJSCallback callback) {
        String userIDListStr = JSONObject.toJSONString(filterUserIDList);
        Open_im_sdk.getGroupMemberListByJoinTimeFilter(new BaseImpl(callback), operationID, groupID, offset, count, joinTimeBegin,joinTimeEnd,userIDListStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getJoinedGroupList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getJoinedGroupList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void createGroup(String operationID, JSONObject gInfo, List memberList, UniJSCallback callback) {
        String infoStr = JSON.toJSONString(gInfo);
        String listStr = JSON.toJSONString(memberList);
        Open_im_sdk.createGroup(new BaseImpl(callback), operationID, infoStr, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupInfo(String operationID, String groupID, JSONObject jsonGroupInfo, UniJSCallback callback) {
        String gInfoStr = JSON.toJSONString(jsonGroupInfo);
        Open_im_sdk.setGroupInfo(new BaseImpl(callback), operationID, groupID, gInfoStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getGroupsInfo(String operationID, List groupIdList, UniJSCallback callback) {
        String listStr = JSON.toJSONString(groupIdList);
        Open_im_sdk.getGroupsInfo(new BaseImpl(callback), operationID, listStr);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void joinGroup(String operationID, String groupId, String message,Integer joinSource, UniJSCallback callback) {
        Open_im_sdk.joinGroup(new BaseImpl(callback), operationID, groupId, message,joinSource);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void quitGroup(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.quitGroup(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void transferGroupOwner(String operationID, String groupId, String userId, UniJSCallback callback) {
        Open_im_sdk.transferGroupOwner(new BaseImpl(callback), operationID, groupId, userId);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void dismissGroup(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.dismissGroup(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void changeGroupMute(String operationID, String groupID, Boolean isMute, UniJSCallback callback) {
        Open_im_sdk.changeGroupMute(new BaseImpl(callback), operationID, groupID, isMute);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void changeGroupMemberMute(String operationID, String groupID, String userID, Integer mutedSeconds, UniJSCallback callback) {
        Open_im_sdk.changeGroupMemberMute(new BaseImpl(callback), operationID, groupID, userID, (long)mutedSeconds);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupMemberNickname(String operationID, String groupID, String userID, String groupMemberNickname, UniJSCallback callback) {
        Open_im_sdk.setGroupMemberNickname(new BaseImpl(callback), operationID, groupID, userID, groupMemberNickname);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupMemberRoleLevel(String operationID, String groupID, String userID, Integer roleLevel, UniJSCallback callback) {
        Open_im_sdk.setGroupMemberRoleLevel(new BaseImpl(callback), operationID, groupID, userID, roleLevel);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupVerification(String operationID, String groupID, Integer verification, UniJSCallback callback) {
        Open_im_sdk.setGroupVerification(new BaseImpl(callback), operationID, groupID, verification);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void searchGroups(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.searchGroups(new BaseImpl(callback), operationID, options.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getRecvGroupApplicationList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getRecvGroupApplicationList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getSendGroupApplicationList(String operationID, UniJSCallback callback) {
        Open_im_sdk.getSendGroupApplicationList(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void acceptGroupApplication(String operationID, String groupID, String fromUserID, String handleMsg, UniJSCallback callback) {
        Open_im_sdk.acceptGroupApplication(new BaseImpl(callback), operationID, groupID, fromUserID, handleMsg);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void refuseGroupApplication(String operationID, String groupID, String fromUserID, String handleMsg, UniJSCallback callback) {
        Open_im_sdk.refuseGroupApplication(new BaseImpl(callback), operationID, groupID, fromUserID, handleMsg);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGlobalRecvMessageOpt(String operationID, Integer opt, UniJSCallback callback) {
        Open_im_sdk.setGlobalRecvMessageOpt(new BaseImpl(callback), operationID, opt );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void resetConversationGroupAtType(String operationID, String conversationID, UniJSCallback callback) {
        Open_im_sdk.resetConversationGroupAtType(new BaseImpl(callback), operationID, conversationID );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingInvite(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingInvite(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingInviteInGroup(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingInviteInGroup(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingAccept(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingAccept(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingReject(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingReject(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingCancel(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingCancel(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingHungUp(String operationID, JSONObject options, UniJSCallback callback) {
        Open_im_sdk.signalingHungUp(new BaseImpl(callback), operationID, options.toJSONString() );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getSubDepartment(String operationID, String departmentID,Integer offset,Integer count, UniJSCallback callback) {
        Open_im_sdk.getSubDepartment(new BaseImpl(callback), operationID, departmentID,offset,count );
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getDepartmentMember(String operationID, String departmentID,Integer offset,Integer count, UniJSCallback callback) {
        Open_im_sdk.getDepartmentMember(new BaseImpl(callback), operationID, departmentID,offset,count );
    }


     @UniJSMethod(
                uiThread = false
        )
        public void getUserInDepartment(String operationID, String userID, UniJSCallback callback) {
            Open_im_sdk.getUserInDepartment(new BaseImpl(callback), operationID, userID);
        }

    @UniJSMethod(
            uiThread = false
    )
    public void getDepartmentMemberAndSubDepartment(String operationID, String departmentID, UniJSCallback callback) {
        Open_im_sdk.getDepartmentMemberAndSubDepartment(new BaseImpl(callback), operationID, departmentID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getDepartmentInfo(String operationID, String departmentID, UniJSCallback callback) {
        Open_im_sdk.getDepartmentInfo(new BaseImpl(callback), operationID, departmentID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void searchOrganization(String operationID, JSONObject options, Integer offset,Integer count, UniJSCallback callback) {
        Open_im_sdk.searchOrganization(new BaseImpl(callback), operationID, options.toJSONString(),offset,count);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getWorkMomentsUnReadCount(String operationID, UniJSCallback callback) {
        Open_im_sdk.getWorkMomentsUnReadCount(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getWorkMomentsNotification(String operationID,Integer offset,Integer count,  UniJSCallback callback) {
        Open_im_sdk.getWorkMomentsNotification(new BaseImpl(callback), operationID,offset,count);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupApplyMemberFriend(String operationID,String groupID,Integer rule, UniJSCallback callback) {
        Open_im_sdk.setGroupApplyMemberFriend(new BaseImpl(callback), operationID,groupID,rule);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setGroupLookMemberInfo(String operationID,String groupID,Integer rule, UniJSCallback callback) {
        Open_im_sdk.setGroupLookMemberInfo(new BaseImpl(callback), operationID,groupID,rule);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void clearWorkMomentsNotification(String operationID, UniJSCallback callback) {
        Open_im_sdk.clearWorkMomentsNotification(new BaseImpl(callback), operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void newRevokeMessage(String operationID,JSONObject revokeMsg, UniJSCallback callback) {
        Open_im_sdk.newRevokeMessage(new BaseImpl(callback), operationID,revokeMsg.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public void findMessageList(String operationID,JSONObject findOptions, UniJSCallback callback) {
        Open_im_sdk.findMessageList(new BaseImpl(callback), operationID,findOptions.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public void getAdvancedHistoryMessageList(String operationID,JSONObject findOptions, UniJSCallback callback) {
        Open_im_sdk.getAdvancedHistoryMessageList(new BaseImpl(callback), operationID,findOptions.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public String getAtAllTag() {
        return Open_im_sdk.getAtAllTag();
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setBatchMsgListener() {
        OnBatchMsgListener batchMsgListener = new OnBatchMsgListener() {
            @Override
            public void onRecvNewMessages(String s) {
                Map<String, Object> params = new HashMap();
                params.put("messages", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("onRecvNewMessages", params);
            }
        };
        Open_im_sdk.setBatchMsgListener(batchMsgListener);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void searchGroupMembers(String operationID,JSONObject searchOptions, UniJSCallback callback) {
        Open_im_sdk.searchGroupMembers(new BaseImpl(callback), operationID,searchOptions.toJSONString());
    }

    @UniJSMethod(
            uiThread = false
    )
    public String getSdkVersion() {
        return Open_im_sdk.sdkVersion();
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setAppBackgroundStatus(String operationID,boolean isBackground, UniJSCallback callback) {
        Open_im_sdk.setAppBackgroundStatus(new BaseImpl(callback), operationID,isBackground);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setOneConversationBurnDuration(String operationID, String conversationID, Integer burnDuration, UniJSCallback callback) {
        Open_im_sdk.setOneConversationBurnDuration(new BaseImpl(callback), operationID, conversationID, burnDuration);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingGetRoomByGroupID(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.signalingGetRoomByGroupID(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void signalingGetTokenByRoomID(String operationID, String groupID, UniJSCallback callback) {
        Open_im_sdk.signalingGetTokenByRoomID(new BaseImpl(callback), operationID, groupID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void setAppBadge(String operationID, Integer appUnreadCount, UniJSCallback callback) {
        Open_im_sdk.setAppBadge(new BaseImpl(callback),appUnreadCount, operationID);
    }

    @UniJSMethod(
            uiThread = false
    )
    public void uploadFile(String operationID, String filePath) {
        SendMsgCallBack uploadCallback = new SendMsgCallBack() {
            public void onError(int i, String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", i);
                params.put("errMsg", s);
                params.put("operationID", operationID);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("uploadFileFailed", params);
            }

            public void onProgress(long l) {
                Map<String, Object> params = new HashMap();
                params.put("progress", l);
                params.put("operationID", operationID);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("uploadFileProgress", params);
            }

            public void onSuccess(String s) {
                Map<String, Object> params = new HashMap();
                params.put("errCode", 0);
                params.put("errMsg", "");
                params.put("operationID", operationID);
                params.put("data", s);
                OpenIMSDK.this.mUniSDKInstance.fireGlobalEventCallback("uploadFileSuccess", params);
            }
        };
        Open_im_sdk.uploadFile(uploadCallback, operationID, filePath);
    }
}

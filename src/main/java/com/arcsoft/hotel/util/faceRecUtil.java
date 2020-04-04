package com.arcsoft.hotel.util;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

public class faceRecUtil {
    private String appId = "C5NVXpX7DuGy7AY85kEyzwafTR679m8g4tLeuCUDBZuG";
    //private String sdkKey = "hhhhhhh";//test
    private String sdkKey = "F3Dx79kGs9cTkMgpKGj4dAepqF64PtKFrFdgE2ukMXYM";//windoes
    //private String sdkKey = "F3Dx79kGs9cTkMgpKGj4dAeph8WstqnoMsPREpMBwyR8";//linux
    private FaceEngine faceEngine = null;


    public faceRecUtil(String path) {

        faceEngine = new FaceEngine(path);
        //激活引擎
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败,activeCode=" + activeCode);
        } else System.out.println("引擎激活成功");

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY);

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);

        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败+initCode=" + initCode);
            return;
        } else System.out.println("初始化引擎成功");

        //获取激活文件信息
        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
        int activeFileCode = faceEngine.getActiveFileInfo(activeFileInfo);
        System.out.println("activeFileCode=" + activeFileCode);
    }

    //人脸检测
    public List<FaceInfo> getFaceInfo(File file) {
        ImageInfo imageInfo = getRGBData(file);
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        System.out.println(faceInfoList);
        System.out.println("detectCode=" + detectCode);
        return faceInfoList;
    }

    //特征提取
    public FaceFeature getFaceFeature(File file) {
        ImageInfo imageInfo = getRGBData(file);
        List<FaceInfo> faceInfoList = getFaceInfo(file);
        FaceFeature faceFeature = new FaceFeature();
        int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);
        System.out.println(" extractCode=" + extractCode);
        return faceFeature;
    }

    public float getFaceSimilar(byte[] face1, byte[] face2) {
        FaceFeature faceFeature = new FaceFeature(face1);
        FaceFeature faceFeature2 = new FaceFeature(face2);
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度：" + faceSimilar.getScore());
        System.out.println(" compareCode" + compareCode);
        return faceSimilar.getScore();
    }

    public float getFaceSimilar(File file, byte[] face2) {
        FaceFeature faceFeature2 = new FaceFeature(face2);
        FaceFeature faceFeature = getFaceFeature(file);
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度：" + faceSimilar.getScore());
        System.out.println(" compareCode" + compareCode);
        return faceSimilar.getScore();
    }

    public float getFaceSimilar(File file, File file2) {
        FaceFeature faceFeature = getFaceFeature(file);
        FaceFeature faceFeature2 = getFaceFeature(file2);
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度：" + faceSimilar.getScore());
        System.out.println(" compareCode" + compareCode);
        return faceSimilar.getScore();
    }

    public int unInitEngine() {
        //引擎卸载
        return faceEngine.unInit();
    }
}

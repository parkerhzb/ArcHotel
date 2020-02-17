package com.arcsoft.hotel.service.impl;


import com.arcsoft.face.FaceInfo;
import com.arcsoft.hotel.service.faceService;
import com.arcsoft.hotel.util.faceRecUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class faceServiceImpl implements faceService {


    @Override
    public boolean hasFace(String path, String enginePath) {
        File file = new File(path);
        faceRecUtil faceRecUtil = new faceRecUtil(enginePath);
        List<FaceInfo> faceInfos = faceRecUtil.getFaceInfo(file);
        faceRecUtil.unInitEngine();
        if (faceInfos == null || faceInfos.size() == 0)
            return false;
        return true;
    }
}

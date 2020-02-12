package com.arcsoft.hotel.service;

import com.arcsoft.hotel.pojo.Visitor;

import java.util.ArrayList;

public interface visitorService {
    int insertVisitor(Visitor visitor);

    ArrayList<byte[]> getDoorFace(int roomId);
}

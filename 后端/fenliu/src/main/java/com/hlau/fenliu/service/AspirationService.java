package com.hlau.fenliu.service;

import com.hlau.fenliu.repository.AspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AspirationService {
    @Autowired
    AspirationRepository aspirationRepository;

}

package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.practice.model.mapper.ProfileMapper;

public abstract class AbstractRestController {

	@Autowired
	ProfileMapper memberMapper;
}

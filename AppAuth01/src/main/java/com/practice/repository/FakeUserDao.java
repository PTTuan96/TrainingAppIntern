//package com.practice.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import com.practice.auth.AppUser;
//import com.practice.auth.UserDao;
//import com.google.common.collect.Lists;
//
//import static com.practice.security.Role.*;
//
//
//@Repository("fake")
//public class FakeUserDao implements UserDao{
//	private final PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	public FakeUserDao(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder;
//	}
//	
//	@Override
//	public Optional<AppUser> selectUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		return getAppUser()
//				.stream()
//				.filter(appUser -> username.equals(appUser.getUsername()))
//				.findFirst();
//	}	
//	
//	private List<AppUser> getAppUser(){
//		List<AppUser> appUsers = Lists.newArrayList(
//				new AppUser(
//					USER.getGrantedAuthorities(),
//					passwordEncoder.encode("123"),
//					"leanh",
//					true,
//					true,
//					true,
//					true
//				),
//				new AppUser(
//						ADMIN.getGrantedAuthorities(),
//						passwordEncoder.encode("123"),
//						"tuanthanh",
//						true,
//						true,
//						true,
//						true
//					)
//		);
//		return appUsers;
//	}
//
//
//
//	
//}

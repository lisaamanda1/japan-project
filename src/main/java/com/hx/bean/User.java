package com.hx.bean;

public class User {
    private String userId;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
//    private Set<Role> roles;
    private String userRole;

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
  		return userRole;
  	}

  	public void setUserRole(String userRole) {
  		this.userRole = userRole;
  	}
  	
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
    
	public User(String userId, String userName, String password, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
//        this.roles = roles;
        this.userRole = userRole;
    }

	public User() {}
}

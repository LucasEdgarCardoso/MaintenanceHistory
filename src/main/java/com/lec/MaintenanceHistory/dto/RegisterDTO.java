package com.lec.MaintenanceHistory.dto;

import com.lec.MaintenanceHistory.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}

package com.kushal.fullstack.customer;

import com.kushal.fullstack.customer.model.Gender;

public record CustomerRegistrationRequest(String name, String email, int age, Gender gender) {}

package com.kushal.fullstack.customer;

import com.kushal.fullstack.customer.model.Gender;

public record CustomerUpdateRequest(String name, String email, Integer age, Gender gender) {}

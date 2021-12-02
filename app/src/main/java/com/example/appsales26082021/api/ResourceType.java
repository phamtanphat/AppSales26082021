package com.example.appsales26082021.api;

public class ResourceType<T> {
    public T data;
    public String message;
    public Status status;

    public ResourceType(T data, Status status) {
        this.data = data;
        this.status = status;
    }

    public ResourceType(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public static class Success<T> extends ResourceType<T> {
        public Success(T data) {
            super(data, Status.SUCCESS);
        }
    }

    public static class Error<T> extends ResourceType<T> {
        public Error(String message) {
            super(message, Status.ERROR);
        }
    }

    public static class Loading<T> extends ResourceType<T> {
        public Loading(T data) {
            super(data, Status.LOADING);
        }
    }

    public enum Status {
        SUCCESS, LOADING, ERROR
    }
}

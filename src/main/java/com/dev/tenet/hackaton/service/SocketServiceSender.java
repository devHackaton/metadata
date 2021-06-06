package com.dev.tenet.hackaton.service;

public interface SocketServiceSender<T> {
    void sendTo(Long id, T t);
}

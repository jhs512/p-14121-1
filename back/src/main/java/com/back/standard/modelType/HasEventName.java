package com.back.standard.modelType;

public interface HasEventName {
    default String getEventName() {
        return this.getClass().getSimpleName();
    }
}

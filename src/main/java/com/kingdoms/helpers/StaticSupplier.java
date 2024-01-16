package com.kingdoms.helpers;

import java.util.function.Supplier;

public class StaticSupplier<T> implements Supplier<T> {
  T data;

  public StaticSupplier(T data) {
    this.data = data;
  }

  @Override
  public T get() {
    return data;
  }

  public void set(T data) {
    this.data = data;
  }
}

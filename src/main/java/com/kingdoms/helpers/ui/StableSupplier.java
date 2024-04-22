package com.kingdoms.helpers.ui;

import java.util.function.Supplier;

/**
 * Since UI Elements have some values set by Suppliers, this
 * class is used to provide a static value
 */
public class StableSupplier<T> implements Supplier<T> {
  T data;

  public StableSupplier(T data) {
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

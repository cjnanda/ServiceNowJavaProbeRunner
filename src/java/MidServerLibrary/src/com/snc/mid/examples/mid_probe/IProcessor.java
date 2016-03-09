package com.snc.mid.examples.mid_probe;

public abstract interface IProcessor
{
  public abstract Boolean process();
  
  public abstract Boolean sendComplete();
}

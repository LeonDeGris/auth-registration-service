package com.bmstu.flowrence.mapper;

import java.util.List;

public interface BaseMapper <S, D> {

    D mapSourceToDestination(S source);

    List<D> mapSourceToDestination(List<S> source);
}

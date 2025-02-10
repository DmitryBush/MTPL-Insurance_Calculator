package com.bush.myapplication.database.dao;

public record QueryArgument(String table,
                            String filtration,
                            String[] argsFiltration,
                            String groupBy,
                            String having,
                            String orderBy,
                            String limit) { }

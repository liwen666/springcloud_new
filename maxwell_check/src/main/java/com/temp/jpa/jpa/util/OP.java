package com.temp.jpa.jpa.util;
/**
 * <p>
 *  描述
 * </p>
 *
 * @author lw
 * @since  2020/2/24 20:19
 */
public enum OP {
    // like
    LIKE,
    // =
    EQ,
    // !=
    NOTEQ,
    // >
    GT,
    // >=
    GTEQ,
    // <
    LT,
    // <=
    LTEQ,
    // is null
    NULL,
    // is not null
    NOTNULL,
    // in
    IN,
    // not in
    NOTIN,

    AND,

    OR,

    NOT
}
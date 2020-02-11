package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.CanteenOrder;
import com.arcsoft.hotel.pojo.CanteenOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CanteenOrderMapper {
    @SelectProvider(type = CanteenOrderSqlProvider.class, method = "countByExample")
    int countByExample(CanteenOrderExample example);

    @DeleteProvider(type = CanteenOrderSqlProvider.class, method = "deleteByExample")
    int deleteByExample(CanteenOrderExample example);

    @Delete({
            "delete from canteen_order",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into canteen_order (id, time, ",
            "price, pay_method)",
            "values (#{id,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, ",
            "#{price,jdbcType=DOUBLE}, #{payMethod,jdbcType=VARCHAR})"
    })
    int insert(CanteenOrder record);

    @InsertProvider(type = CanteenOrderSqlProvider.class, method = "insertSelective")
    int insertSelective(CanteenOrder record);

    @SelectProvider(type = CanteenOrderSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "pay_method", property = "payMethod", jdbcType = JdbcType.VARCHAR)
    })
    List<CanteenOrder> selectByExample(CanteenOrderExample example);

    @Select({
            "select",
            "id, time, price, pay_method",
            "from canteen_order",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "pay_method", property = "payMethod", jdbcType = JdbcType.VARCHAR)
    })
    CanteenOrder selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CanteenOrderSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CanteenOrder record, @Param("example") CanteenOrderExample example);

    @UpdateProvider(type = CanteenOrderSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") CanteenOrder record, @Param("example") CanteenOrderExample example);

    @UpdateProvider(type = CanteenOrderSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CanteenOrder record);

    @Update({
            "update canteen_order",
            "set time = #{time,jdbcType=TIMESTAMP},",
            "price = #{price,jdbcType=DOUBLE},",
            "pay_method = #{payMethod,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CanteenOrder record);
}
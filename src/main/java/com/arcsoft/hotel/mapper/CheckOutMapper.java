package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.CheckOut;
import com.arcsoft.hotel.pojo.CheckOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CheckOutMapper {
    @SelectProvider(type = CheckOutSqlProvider.class, method = "countByExample")
    int countByExample(CheckOutExample example);

    @DeleteProvider(type = CheckOutSqlProvider.class, method = "deleteByExample")
    int deleteByExample(CheckOutExample example);

    @Delete({
            "delete from check_out",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into check_out (checkin_id, price, ",
            "checkout_date, time)",
            "values (#{checkinId,jdbcType=INTEGER}, #{price,jdbcType=DOUBLE}, ",
            "#{checkoutDate,jdbcType=DATE}, #{time,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(CheckOut record);

    @InsertProvider(type = CheckOutSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(CheckOut record);

    @SelectProvider(type = CheckOutSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR)
    })
    List<CheckOut> selectByExample(CheckOutExample example);

    @Select({
            "select",
            "id, checkin_id, price, checkout_date, time",
            "from check_out",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR)
    })
    CheckOut selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CheckOutSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CheckOut record, @Param("example") CheckOutExample example);

    @UpdateProvider(type = CheckOutSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") CheckOut record, @Param("example") CheckOutExample example);

    @UpdateProvider(type = CheckOutSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckOut record);

    @Update({
            "update check_out",
            "set checkin_id = #{checkinId,jdbcType=INTEGER},",
            "price = #{price,jdbcType=DOUBLE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "time = #{time,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CheckOut record);
}
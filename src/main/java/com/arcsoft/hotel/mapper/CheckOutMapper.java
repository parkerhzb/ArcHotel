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
            "insert into check_out (id, room_id, ",
            "price, checkin_date, ",
            "checkout_date, time, ",
            "name, document_type, ",
            "document_number, checkin_id)",
            "values (#{id,jdbcType=INTEGER}, #{roomId,jdbcType=INTEGER}, ",
            "#{price,jdbcType=DOUBLE}, #{checkinDate,jdbcType=DATE}, ",
            "#{checkoutDate,jdbcType=DATE}, #{time,jdbcType=TIMESTAMP}, ",
            "#{name,jdbcType=VARCHAR}, #{documentType,jdbcType=INTEGER}, ",
            "#{documentNumber,jdbcType=VARCHAR}, #{checkinId,jdbcType=INTEGER})"
    })
    int insert(CheckOut record);

    @InsertProvider(type = CheckOutSqlProvider.class, method = "insertSelective")
    int insertSelective(CheckOut record);

    @SelectProvider(type = CheckOutSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER)
    })
    List<CheckOut> selectByExample(CheckOutExample example);

    @Select({
            "select",
            "id, room_id, price, checkin_date, checkout_date, time, name, document_type, ",
            "document_number, checkin_id",
            "from check_out",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER)
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
            "set room_id = #{roomId,jdbcType=INTEGER},",
            "price = #{price,jdbcType=DOUBLE},",
            "checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "name = #{name,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "checkin_id = #{checkinId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CheckOut record);
}
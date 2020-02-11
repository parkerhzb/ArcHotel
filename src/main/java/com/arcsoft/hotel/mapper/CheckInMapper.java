package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.CheckIn;
import com.arcsoft.hotel.pojo.CheckInExample;

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

public interface CheckInMapper {
    @SelectProvider(type = CheckInSqlProvider.class, method = "countByExample")
    int countByExample(CheckInExample example);

    @DeleteProvider(type = CheckInSqlProvider.class, method = "deleteByExample")
    int deleteByExample(CheckInExample example);

    @Delete({
            "delete from check_in",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into check_in (id, name, ",
            "document_type, document_number, ",
            "gender, checkin_date, ",
            "checkout_date, time, ",
            "room_id, related_room, ",
            "is_check_out, phone_number, ",
            "face)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{documentType,jdbcType=INTEGER}, #{documentNumber,jdbcType=VARCHAR}, ",
            "#{gender,jdbcType=VARCHAR}, #{checkinDate,jdbcType=DATE}, ",
            "#{checkoutDate,jdbcType=DATE}, #{time,jdbcType=VARCHAR}, ",
            "#{roomId,jdbcType=INTEGER}, #{relatedRoom,jdbcType=VARCHAR}, ",
            "#{isCheckOut,jdbcType=TINYINT}, #{phoneNumber,jdbcType=VARCHAR}, ",
            "#{face,jdbcType=VARBINARY})"
    })
    int insert(CheckIn record);

    @InsertProvider(type = CheckInSqlProvider.class, method = "insertSelective")
    int insertSelective(CheckIn record);

    @SelectProvider(type = CheckInSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "related_room", property = "relatedRoom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<CheckIn> selectByExampleWithBLOBs(CheckInExample example);

    @SelectProvider(type = CheckInSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "related_room", property = "relatedRoom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR)
    })
    List<CheckIn> selectByExample(CheckInExample example);

    @Select({
            "select",
            "id, name, document_type, document_number, gender, checkin_date, checkout_date, ",
            "time, room_id, related_room, is_check_out, phone_number, face",
            "from check_in",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "document_type", property = "documentType", jdbcType = JdbcType.INTEGER),
            @Result(column = "document_number", property = "documentNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "related_room", property = "relatedRoom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT),
            @Result(column = "phone_number", property = "phoneNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    CheckIn selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckIn record);

    @Update({
            "update check_in",
            "set name = #{name,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=VARCHAR},",
            "checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "time = #{time,jdbcType=VARCHAR},",
            "room_id = #{roomId,jdbcType=INTEGER},",
            "related_room = #{relatedRoom,jdbcType=VARCHAR},",
            "is_check_out = #{isCheckOut,jdbcType=TINYINT},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(CheckIn record);

    @Update({
            "update check_in",
            "set name = #{name,jdbcType=VARCHAR},",
            "document_type = #{documentType,jdbcType=INTEGER},",
            "document_number = #{documentNumber,jdbcType=VARCHAR},",
            "gender = #{gender,jdbcType=VARCHAR},",
            "checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "time = #{time,jdbcType=VARCHAR},",
            "room_id = #{roomId,jdbcType=INTEGER},",
            "related_room = #{relatedRoom,jdbcType=VARCHAR},",
            "is_check_out = #{isCheckOut,jdbcType=TINYINT},",
            "phone_number = #{phoneNumber,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CheckIn record);
}
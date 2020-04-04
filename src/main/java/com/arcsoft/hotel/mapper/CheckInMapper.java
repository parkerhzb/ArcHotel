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
import org.apache.ibatis.annotations.SelectKey;
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
            "insert into check_in (checkin_date, checkout_date, ",
            "time, room_id, related_room, ",
            "person_num, is_check_out)",
            "values (#{checkinDate,jdbcType=DATE}, #{checkoutDate,jdbcType=DATE}, ",
            "#{time,jdbcType=VARCHAR}, #{roomId,jdbcType=INTEGER}, #{relatedRoom,jdbcType=VARCHAR}, ",
            "#{personNum,jdbcType=INTEGER}, #{isCheckOut,jdbcType=TINYINT})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(CheckIn record);

    @InsertProvider(type = CheckInSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(CheckIn record);

    @SelectProvider(type = CheckInSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "related_room", property = "relatedRoom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "person_num", property = "personNum", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT)
    })
    List<CheckIn> selectByExample(CheckInExample example);

    @Select({
            "select",
            "id, checkin_date, checkout_date, time, room_id, related_room, person_num, is_check_out",
            "from check_in",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_date", property = "checkinDate", jdbcType = JdbcType.DATE),
            @Result(column = "checkout_date", property = "checkoutDate", jdbcType = JdbcType.DATE),
            @Result(column = "time", property = "time", jdbcType = JdbcType.VARCHAR),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "related_room", property = "relatedRoom", jdbcType = JdbcType.VARCHAR),
            @Result(column = "person_num", property = "personNum", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_check_out", property = "isCheckOut", jdbcType = JdbcType.TINYINT)
    })
    CheckIn selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") CheckIn record, @Param("example") CheckInExample example);

    @UpdateProvider(type = CheckInSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CheckIn record);

    @Update({
            "update check_in",
            "set checkin_date = #{checkinDate,jdbcType=DATE},",
            "checkout_date = #{checkoutDate,jdbcType=DATE},",
            "time = #{time,jdbcType=VARCHAR},",
            "room_id = #{roomId,jdbcType=INTEGER},",
            "related_room = #{relatedRoom,jdbcType=VARCHAR},",
            "person_num = #{personNum,jdbcType=INTEGER},",
            "is_check_out = #{isCheckOut,jdbcType=TINYINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CheckIn record);
}
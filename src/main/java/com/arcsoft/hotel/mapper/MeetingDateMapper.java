package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.MeetingDate;
import com.arcsoft.hotel.pojo.MeetingDateExample;

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

public interface MeetingDateMapper {
    @SelectProvider(type = MeetingDateSqlProvider.class, method = "countByExample")
    int countByExample(MeetingDateExample example);

    @DeleteProvider(type = MeetingDateSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MeetingDateExample example);

    @Delete({
            "delete from meeting_date",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into meeting_date (reserve_id, date, ",
            "timeperiod)",
            "values (#{reserveId,jdbcType=INTEGER}, #{date,jdbcType=DATE}, ",
            "#{timeperiod,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(MeetingDate record);

    @InsertProvider(type = MeetingDateSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(MeetingDate record);

    @SelectProvider(type = MeetingDateSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "reserve_id", property = "reserveId", jdbcType = JdbcType.INTEGER),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "timeperiod", property = "timeperiod", jdbcType = JdbcType.INTEGER)
    })
    List<MeetingDate> selectByExample(MeetingDateExample example);

    @Select({
            "select",
            "id, reserve_id, date, timeperiod",
            "from meeting_date",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "reserve_id", property = "reserveId", jdbcType = JdbcType.INTEGER),
            @Result(column = "date", property = "date", jdbcType = JdbcType.DATE),
            @Result(column = "timeperiod", property = "timeperiod", jdbcType = JdbcType.INTEGER)
    })
    MeetingDate selectByPrimaryKey(Integer id);

    @UpdateProvider(type = MeetingDateSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MeetingDate record, @Param("example") MeetingDateExample example);

    @UpdateProvider(type = MeetingDateSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") MeetingDate record, @Param("example") MeetingDateExample example);

    @UpdateProvider(type = MeetingDateSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MeetingDate record);

    @Update({
            "update meeting_date",
            "set reserve_id = #{reserveId,jdbcType=INTEGER},",
            "date = #{date,jdbcType=DATE},",
            "timeperiod = #{timeperiod,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingDate record);
}
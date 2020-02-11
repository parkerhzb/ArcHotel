package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Meeting;
import com.arcsoft.hotel.pojo.MeetingExample;

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

public interface MeetingMapper {
    @SelectProvider(type = MeetingSqlProvider.class, method = "countByExample")
    int countByExample(MeetingExample example);

    @DeleteProvider(type = MeetingSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MeetingExample example);

    @Delete({
            "delete from meeting",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into meeting (id, meeting_number, ",
            "type_id, state)",
            "values (#{id,jdbcType=INTEGER}, #{meetingNumber,jdbcType=VARCHAR}, ",
            "#{typeId,jdbcType=INTEGER}, #{state,jdbcType=VARCHAR})"
    })
    int insert(Meeting record);

    @InsertProvider(type = MeetingSqlProvider.class, method = "insertSelective")
    int insertSelective(Meeting record);

    @SelectProvider(type = MeetingSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_number", property = "meetingNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR)
    })
    List<Meeting> selectByExample(MeetingExample example);

    @Select({
            "select",
            "id, meeting_number, type_id, state",
            "from meeting",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "meeting_number", property = "meetingNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR)
    })
    Meeting selectByPrimaryKey(Integer id);

    @UpdateProvider(type = MeetingSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Meeting record, @Param("example") MeetingExample example);

    @UpdateProvider(type = MeetingSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Meeting record, @Param("example") MeetingExample example);

    @UpdateProvider(type = MeetingSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Meeting record);

    @Update({
            "update meeting",
            "set meeting_number = #{meetingNumber,jdbcType=VARCHAR},",
            "type_id = #{typeId,jdbcType=INTEGER},",
            "state = #{state,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Meeting record);
}
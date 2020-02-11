package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Room;
import com.arcsoft.hotel.pojo.RoomExample;

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

public interface RoomMapper {
    @SelectProvider(type = RoomSqlProvider.class, method = "countByExample")
    int countByExample(RoomExample example);

    @DeleteProvider(type = RoomSqlProvider.class, method = "deleteByExample")
    int deleteByExample(RoomExample example);

    @Delete({
            "delete from room",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into room (id, room_number, ",
            "type_id, state)",
            "values (#{id,jdbcType=INTEGER}, #{roomNumber,jdbcType=VARCHAR}, ",
            "#{typeId,jdbcType=INTEGER}, #{state,jdbcType=VARCHAR})"
    })
    int insert(Room record);

    @InsertProvider(type = RoomSqlProvider.class, method = "insertSelective")
    int insertSelective(Room record);

    @SelectProvider(type = RoomSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_number", property = "roomNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR)
    })
    List<Room> selectByExample(RoomExample example);

    @Select({
            "select",
            "id, room_number, type_id, state",
            "from room",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_number", property = "roomNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type_id", property = "typeId", jdbcType = JdbcType.INTEGER),
            @Result(column = "state", property = "state", jdbcType = JdbcType.VARCHAR)
    })
    Room selectByPrimaryKey(Integer id);

    @UpdateProvider(type = RoomSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Room record, @Param("example") RoomExample example);

    @UpdateProvider(type = RoomSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Room record, @Param("example") RoomExample example);

    @UpdateProvider(type = RoomSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Room record);

    @Update({
            "update room",
            "set room_number = #{roomNumber,jdbcType=VARCHAR},",
            "type_id = #{typeId,jdbcType=INTEGER},",
            "state = #{state,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Room record);
}
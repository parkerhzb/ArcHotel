package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.RoomConsume;
import com.arcsoft.hotel.pojo.RoomConsumeExample;
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

public interface RoomConsumeMapper {
    @SelectProvider(type = RoomConsumeSqlProvider.class, method = "countByExample")
    int countByExample(RoomConsumeExample example);

    @DeleteProvider(type = RoomConsumeSqlProvider.class, method = "deleteByExample")
    int deleteByExample(RoomConsumeExample example);

    @Delete({
            "delete from room_consume",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into room_consume (room_id, time, ",
            "item, price)",
            "values (#{roomId,jdbcType=INTEGER}, #{time,jdbcType=TIMESTAMP}, ",
            "#{item,jdbcType=VARCHAR}, #{price,jdbcType=DOUBLE})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(RoomConsume record);

    @InsertProvider(type = RoomConsumeSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(RoomConsume record);

    @SelectProvider(type = RoomConsumeSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "item", property = "item", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE)
    })
    List<RoomConsume> selectByExample(RoomConsumeExample example);

    @Select({
            "select",
            "id, room_id, time, item, price",
            "from room_consume",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "room_id", property = "roomId", jdbcType = JdbcType.INTEGER),
            @Result(column = "time", property = "time", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "item", property = "item", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE)
    })
    RoomConsume selectByPrimaryKey(Integer id);

    @UpdateProvider(type = RoomConsumeSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoomConsume record, @Param("example") RoomConsumeExample example);

    @UpdateProvider(type = RoomConsumeSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") RoomConsume record, @Param("example") RoomConsumeExample example);

    @UpdateProvider(type = RoomConsumeSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoomConsume record);

    @Update({
            "update room_consume",
            "set room_id = #{roomId,jdbcType=INTEGER},",
            "time = #{time,jdbcType=TIMESTAMP},",
            "item = #{item,jdbcType=VARCHAR},",
            "price = #{price,jdbcType=DOUBLE}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoomConsume record);
}
package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Park;
import com.arcsoft.hotel.pojo.ParkExample;
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

public interface ParkMapper {
    @SelectProvider(type = ParkSqlProvider.class, method = "countByExample")
    int countByExample(ParkExample example);

    @DeleteProvider(type = ParkSqlProvider.class, method = "deleteByExample")
    int deleteByExample(ParkExample example);

    @Delete({
            "delete from park",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into park (car_number, park_time, ",
            "leave_time)",
            "values (#{carNumber,jdbcType=VARCHAR}, #{parkTime,jdbcType=TIMESTAMP}, ",
            "#{leaveTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Park record);

    @InsertProvider(type = ParkSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Park record);

    @SelectProvider(type = ParkSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "car_number", property = "carNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "park_time", property = "parkTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "leave_time", property = "leaveTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<Park> selectByExample(ParkExample example);

    @Select({
            "select",
            "id, car_number, park_time, leave_time",
            "from park",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "car_number", property = "carNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "park_time", property = "parkTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "leave_time", property = "leaveTime", jdbcType = JdbcType.TIMESTAMP)
    })
    Park selectByPrimaryKey(Integer id);

    @UpdateProvider(type = ParkSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Park record, @Param("example") ParkExample example);

    @UpdateProvider(type = ParkSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Park record, @Param("example") ParkExample example);

    @UpdateProvider(type = ParkSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Park record);

    @Update({
            "update park",
            "set car_number = #{carNumber,jdbcType=VARCHAR},",
            "park_time = #{parkTime,jdbcType=TIMESTAMP},",
            "leave_time = #{leaveTime,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Park record);
}
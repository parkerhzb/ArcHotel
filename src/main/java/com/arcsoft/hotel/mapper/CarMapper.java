package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Car;
import com.arcsoft.hotel.pojo.CarExample;
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

public interface CarMapper {
    @SelectProvider(type = CarSqlProvider.class, method = "countByExample")
    int countByExample(CarExample example);

    @DeleteProvider(type = CarSqlProvider.class, method = "deleteByExample")
    int deleteByExample(CarExample example);

    @Delete({
            "delete from car",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into car (car_number, is_checkin, ",
            "flag, face)",
            "values (#{carNumber,jdbcType=VARCHAR}, #{isCheckin,jdbcType=TINYINT}, ",
            "#{flag,jdbcType=INTEGER}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Car record);

    @InsertProvider(type = CarSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Car record);

    @SelectProvider(type = CarSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "car_number", property = "carNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT),
            @Result(column = "flag", property = "flag", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<Car> selectByExampleWithBLOBs(CarExample example);

    @SelectProvider(type = CarSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "car_number", property = "carNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT),
            @Result(column = "flag", property = "flag", jdbcType = JdbcType.INTEGER)
    })
    List<Car> selectByExample(CarExample example);

    @Select({
            "select",
            "id, car_number, is_checkin, flag, face",
            "from car",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "car_number", property = "carNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT),
            @Result(column = "flag", property = "flag", jdbcType = JdbcType.INTEGER),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    Car selectByPrimaryKey(Integer id);

    @UpdateProvider(type = CarSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Car record, @Param("example") CarExample example);

    @UpdateProvider(type = CarSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Car record, @Param("example") CarExample example);

    @UpdateProvider(type = CarSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Car record, @Param("example") CarExample example);

    @UpdateProvider(type = CarSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Car record);

    @Update({
            "update car",
            "set car_number = #{carNumber,jdbcType=VARCHAR},",
            "is_checkin = #{isCheckin,jdbcType=TINYINT},",
            "flag = #{flag,jdbcType=INTEGER},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Car record);

    @Update({
            "update car",
            "set car_number = #{carNumber,jdbcType=VARCHAR},",
            "is_checkin = #{isCheckin,jdbcType=TINYINT},",
            "flag = #{flag,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Car record);
}
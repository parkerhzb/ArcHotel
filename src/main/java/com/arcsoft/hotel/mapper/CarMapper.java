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
            "insert into car (id, is_checkin, ",
            "face)",
            "values (#{id,jdbcType=INTEGER}, #{isCheckin,jdbcType=TINYINT}, ",
            "#{face,jdbcType=VARBINARY})"
    })
    int insert(Car record);

    @InsertProvider(type = CarSqlProvider.class, method = "insertSelective")
    int insertSelective(Car record);

    @SelectProvider(type = CarSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<Car> selectByExampleWithBLOBs(CarExample example);

    @SelectProvider(type = CarSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT)
    })
    List<Car> selectByExample(CarExample example);

    @Select({
            "select",
            "id, is_checkin, face",
            "from car",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "is_checkin", property = "isCheckin", jdbcType = JdbcType.TINYINT),
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
            "set is_checkin = #{isCheckin,jdbcType=TINYINT},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Car record);

    @Update({
            "update car",
            "set is_checkin = #{isCheckin,jdbcType=TINYINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Car record);
}
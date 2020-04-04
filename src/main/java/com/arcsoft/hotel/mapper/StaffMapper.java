package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Staff;
import com.arcsoft.hotel.pojo.StaffExample;
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

public interface StaffMapper {
    @SelectProvider(type = StaffSqlProvider.class, method = "countByExample")
    int countByExample(StaffExample example);

    @DeleteProvider(type = StaffSqlProvider.class, method = "deleteByExample")
    int deleteByExample(StaffExample example);

    @Delete({
            "delete from staff",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into staff (name, face)",
            "values (#{name,jdbcType=VARCHAR}, #{face,jdbcType=VARBINARY})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Staff record);

    @InsertProvider(type = StaffSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Staff record);

    @SelectProvider(type = StaffSqlProvider.class, method = "selectByExampleWithBLOBs")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    List<Staff> selectByExampleWithBLOBs(StaffExample example);

    @SelectProvider(type = StaffSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Staff> selectByExample(StaffExample example);

    @Select({
            "select",
            "id, name, face",
            "from staff",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "face", property = "face", jdbcType = JdbcType.VARBINARY)
    })
    Staff selectByPrimaryKey(Integer id);

    @UpdateProvider(type = StaffSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Staff record, @Param("example") StaffExample example);

    @UpdateProvider(type = StaffSqlProvider.class, method = "updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Staff record, @Param("example") StaffExample example);

    @UpdateProvider(type = StaffSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Staff record, @Param("example") StaffExample example);

    @UpdateProvider(type = StaffSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Staff record);

    @Update({
            "update staff",
            "set name = #{name,jdbcType=VARCHAR},",
            "face = #{face,jdbcType=VARBINARY}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Staff record);

    @Update({
            "update staff",
            "set name = #{name,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Staff record);
}
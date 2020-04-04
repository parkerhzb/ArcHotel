package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.Outperson;
import com.arcsoft.hotel.pojo.OutpersonExample;

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

public interface OutpersonMapper {
    @SelectProvider(type = OutpersonSqlProvider.class, method = "countByExample")
    int countByExample(OutpersonExample example);

    @DeleteProvider(type = OutpersonSqlProvider.class, method = "deleteByExample")
    int deleteByExample(OutpersonExample example);

    @Delete({
            "delete from outperson",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into outperson (checkin_id, checkout_id, ",
            "inperson_id)",
            "values (#{checkinId,jdbcType=INTEGER}, #{checkoutId,jdbcType=INTEGER}, ",
            "#{inpersonId,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(Outperson record);

    @InsertProvider(type = OutpersonSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(Outperson record);

    @SelectProvider(type = OutpersonSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "checkout_id", property = "checkoutId", jdbcType = JdbcType.INTEGER),
            @Result(column = "inperson_id", property = "inpersonId", jdbcType = JdbcType.INTEGER)
    })
    List<Outperson> selectByExample(OutpersonExample example);

    @Select({
            "select",
            "id, checkin_id, checkout_id, inperson_id",
            "from outperson",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "checkin_id", property = "checkinId", jdbcType = JdbcType.INTEGER),
            @Result(column = "checkout_id", property = "checkoutId", jdbcType = JdbcType.INTEGER),
            @Result(column = "inperson_id", property = "inpersonId", jdbcType = JdbcType.INTEGER)
    })
    Outperson selectByPrimaryKey(Integer id);

    @UpdateProvider(type = OutpersonSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Outperson record, @Param("example") OutpersonExample example);

    @UpdateProvider(type = OutpersonSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Outperson record, @Param("example") OutpersonExample example);

    @UpdateProvider(type = OutpersonSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Outperson record);

    @Update({
            "update outperson",
            "set checkin_id = #{checkinId,jdbcType=INTEGER},",
            "checkout_id = #{checkoutId,jdbcType=INTEGER},",
            "inperson_id = #{inpersonId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Outperson record);
}
package com.arcsoft.hotel.mapper;

import com.arcsoft.hotel.pojo.MeetingType;
import com.arcsoft.hotel.pojo.MeetingTypeExample;

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

public interface MeetingTypeMapper {
    @SelectProvider(type = MeetingTypeSqlProvider.class, method = "countByExample")
    int countByExample(MeetingTypeExample example);

    @DeleteProvider(type = MeetingTypeSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MeetingTypeExample example);

    @Delete({
            "delete from meeting_type",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into meeting_type (id, price)",
            "values (#{id,jdbcType=INTEGER}, #{price,jdbcType=DOUBLE})"
    })
    int insert(MeetingType record);

    @InsertProvider(type = MeetingTypeSqlProvider.class, method = "insertSelective")
    int insertSelective(MeetingType record);

    @SelectProvider(type = MeetingTypeSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE)
    })
    List<MeetingType> selectByExample(MeetingTypeExample example);

    @Select({
            "select",
            "id, price",
            "from meeting_type",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "price", property = "price", jdbcType = JdbcType.DOUBLE)
    })
    MeetingType selectByPrimaryKey(Integer id);

    @UpdateProvider(type = MeetingTypeSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MeetingType record, @Param("example") MeetingTypeExample example);

    @UpdateProvider(type = MeetingTypeSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") MeetingType record, @Param("example") MeetingTypeExample example);

    @UpdateProvider(type = MeetingTypeSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MeetingType record);

    @Update({
            "update meeting_type",
            "set price = #{price,jdbcType=DOUBLE}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MeetingType record);
}
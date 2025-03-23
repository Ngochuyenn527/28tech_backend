package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.NumberUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
//        Long staffId = buildingSearchBuilder.getStaffId();
//        if (NumberUtils.checkNumber(staffId)) {
//            sql.append(" INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
//        }
//        List<String> typeCode = buildingSearchBuilder.getTypeCode();
//        if (typeCode != null && typeCode.size() != 0) {
//            sql.append(" INNER JOIN buildingrenttype brt ON b.id = brt.buildingid  ");
//            sql.append(" INNER JOIN renttype ON renttype.id = brt.renttypeid ");
//        }

        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (NumberUtils.checkNumber(rentAreaTo) || NumberUtils.checkNumber(rentAreaFrom)) {
            sql.append(" INNER JOIN rentarea ON rentarea.buildingid = b.id ");
        }
    }

    // field cua chinh bang do (ngoai tru cac field tinh toan phuc tap)
    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = field.get(buildingSearchBuilder);
                    if (value != null) {
                        // check là số hay chuỗi
                        if (field.getType().getName().equals("java.lang.Long")
                                || field.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        } else if (field.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE  '%" + value + "%' ");
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // field phai join voi cac bang khac va cac cau querry tinh toan phuc tap cua chinh no
    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//        Long staffId = buildingSearchBuilder.getStaffId();
//        if (NumberUtils.checkNumber(staffId)) {
//            where.append(" AND ab.staffid = " + staffId);
//        }

        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (NumberUtils.checkNumber(rentAreaTo) || NumberUtils.checkNumber(rentAreaFrom)) {
            if (NumberUtils.checkNumber(rentAreaFrom)) {
                where.append(" AND rentarea.value >= " + rentAreaFrom);
            }
            if (NumberUtils.checkNumber(rentAreaTo)) {
                where.append(" AND rentarea.value <= " + rentAreaTo);
            }

        }

        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if (NumberUtils.checkNumber(rentPriceTo) || NumberUtils.checkNumber(rentPriceFrom)) {
            if (NumberUtils.checkNumber(rentPriceFrom)) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }
            if (NumberUtils.checkNumber(rentPriceTo)) {
                where.append(" AND b.rentprice <= " + rentPriceTo);
            }
        }

        // java 7
//		if (typeCode != null && typeCode.size() != 0) {
//			List<String> code = new ArrayList<String>();
//			for (String item : typeCode) {
//				code.add("'" + item + "'");
//			}
//			where.append(" AND rt.code IN(" + String.join(",", code) + ")");
//		}

        // java 8
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            where.append(" AND(");
            String sql = typeCode.stream().map(it -> "b.type Like" + "'%" + it + "%'")
                    .collect(Collectors.joining(" OR "));
            // => AND(renttype.code Like '%tang-tret%' OR renttype.code Like '%nguyen-can%'
            // OR renttype.code Like '%noi-that%')
            where.append(sql);
            where.append(" ) ");
        }

    }


    @Override
    public List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b    ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");

        joinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);

        where.append(" GROUP BY b.id ;");
        sql.append(where);

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingSearchResponse buildingSearchResponse) {
        String sql = buildQueryFilter(buildingSearchResponse.getId());
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(Long id) {
        String sql = "SELECT * FROM building b where b.id = " + id;
        return sql;
    }


}


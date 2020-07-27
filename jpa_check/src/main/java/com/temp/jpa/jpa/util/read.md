Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(number, size, sort);

        Specification spec;
        /***********************单条件查询*************************/
        // 方式1
        Predication p = Predication.get(OP.EQ, "name", name);
        spec = SpecificationFactory.where(p);
        // 方式2
        spec = SpecificationFactory.equal("name", name);
        /***********************多条件查询*************************/
        List<Predication> ps = new ArrayList<>();
        ps.add(Predication.get(OP.LIKE, "name", name));
        ps.add(Predication.get(OP.EQ, "age", age));
        // 全and连接
        spec = SpecificationFactory.where(ps);
        // 全or连接
        spec = SpecificationFactory.or(ps);
        // and和or混合连接
        
        // where name like ?1 and age = ?2
        // and name like ?3 and age = ?4
        // or name like ?5 or age = ?6
        // 工具类实现
        spec = SpecificationFactory.wheres(SpecificationFactory.where(ps))
                .and(SpecificationFactory.where(ps))
                .or(SpecificationFactory.or(ps))
                .build();
        // JPA API辅助类实现
        spec = Specifications.where(SpecificationFactory.where(ps))
                .and(SpecificationFactory.where(ps))
                .or(SpecificationFactory.where(ps));
        
        // where name like ?1 and age = ?2
        // and ( name like ?3 or age = ?4 )
        // 工具类实现
        spec = SpecificationFactory.wheres(SpecificationFactory.where(ps))
                .and(SpecificationFactory.or(ps))
                .build();
        // JPA API辅助类实现
        spec = Specifications.where(SpecificationFactory.where(ps))
                .and(SpecificationFactory.or(ps));

        Page<ConsultChat> chatPage = consultChatDao.findAll(spec, pageable);
package com.htmlTips.repositories;

import com.htmlTips.models.Tip;
import org.springframework.data.repository.CrudRepository;

public interface TipRepository extends CrudRepository<Tip,Long> { }

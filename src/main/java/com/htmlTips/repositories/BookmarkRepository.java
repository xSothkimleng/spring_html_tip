package com.htmlTips.repositories;

import com.htmlTips.models.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark,Long> { }

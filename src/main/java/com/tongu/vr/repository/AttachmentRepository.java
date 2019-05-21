package com.tongu.vr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.tongu.vr.model.entity.Attachment;

public interface AttachmentRepository extends Repository<Attachment, Long>, JpaRepository<Attachment,Long> {

	Attachment findByAttachmentSign(String sign);
}

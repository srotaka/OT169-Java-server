package com.alkemy.ong.utils;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.entity.*;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Mapper {
	@Autowired
	private OrganizationRepository orgRepository;

	public static OrganizationResponseDto mapToDto(Organization organization, OrganizationResponseDto dto) {
		dto.setName(organization.getName());
		dto.setImage(organization.getImage());
		dto.setPhone(organization.getPhone());
		dto.setAddress(organization.getAddress());

		Map<String, String> contactUrls = new HashMap<String, String>();
		contactUrls.put("Email", organization.getEmail());
		contactUrls.put("Facebook", organization.getFacebookUrl());
		contactUrls.put("LinkedIn", organization.getLinkedinUrl());
		contactUrls.put("Instagram", organization.getInstagramUrl());

		dto.setContact(contactUrls);

		return dto;
	}

	public static Organization mapFromDto(OrganizationRequestDto dto, Organization organization) {
		organization.setName(dto.getName());
		organization.setImage(dto.getImage());
		organization.setEmail(dto.getEmail());
		organization.setFacebookUrl(dto.getFacebookUrl());
		organization.setLinkedinUrl(dto.getLinkedinUrl());
		organization.setInstagramUrl(dto.getInstagramUrl());
		organization.setPhone(dto.getPhone());
		organization.setAddress(dto.getAddress());
		organization.setWelcomeText(dto.getWelcomeText());
		organization.setAboutUsText(dto.getAboutUsText());
		return organization;

	}


	public static Category mapToEntity(CategoryBasicDto categoryDto, Category category) {
			category.setName(categoryDto.getName());
		return category;
	}

	public static CategoryBasicDto mapToDto(Category category, CategoryBasicDto basicDto) {
		basicDto.setName(category.getName());

		return basicDto;
	}

	public static ActivityDto mapToDto(Activity activity, ActivityDto dto) {
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		dto.setContent(activity.getContent());
		dto.setImage(activity.getImage());
		return dto;
	}

	public static Activity mapFromDto(ActivityDto dto, Activity activity) {
		activity.setName(dto.getName());
		activity.setContent(dto.getContent());
		activity.setImage(dto.getImage());
		return activity;
	}

	public static UserDto mapToUserDto(User user) {

		return new UserDto(
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPhoto());
	}

	public static SlideDto mapToDto(Slide slide, SlideDto slideDto){
		slideDto.setImageUrl(slide.getImageUrl());
		slideDto.setOrder(slide.getOrder());
		return slideDto;
	}

	public SlideResponseDto fullSlideToDto(Slide slide){

		SlideResponseDto dto = new SlideResponseDto();
		OrganizationResponseDto orgDto = new OrganizationResponseDto();
		//Modifation find by Ong
		String idOrg= slide.getOrganizationId().getId();
		/*Creation OrganizationDto*/
		Organization organizationEntity = orgRepository.findById(idOrg).get();
		OrganizationResponseDto last= Mapper.mapToDto(organizationEntity, orgDto);
		dto.setImgUrl(slide.getImageUrl());
		dto.setOrder(slide.getOrder());
		dto.setText(slide.getText());
		dto.setOrg(last);

		return dto;
	}

	public static CommentResponseDto mapToDto(Comment comment, CommentResponseDto dto) {
		dto.setBody(comment.getBody());
		return dto;
	}

	public static ContactDto mapToDto(Contact contact, ContactDto contactDto){
		contactDto.setId(contact.getId());
		contactDto.setName(contact.getName());
		contactDto.setPhone(contact.getPhone());
		contactDto.setEmail(contact.getEmail());
		contactDto.setMessage(contact.getMessage());
		return contactDto;
	}

	public static TestimonialDto mapToDto(Testimonial testimonial, TestimonialDto testimonialDto){
		testimonialDto.setId(testimonial.getId());
		testimonialDto.setName(testimonial.getName());
		testimonialDto.setImage(testimonial.getImage());
		testimonialDto.setContent(testimonial.getContent());

		return testimonialDto;
	}

	public static Testimonial mapFromDto(TestimonialDto testimonialDto, Testimonial testimonial){
		testimonial.setName(testimonialDto.getName());
		testimonial.setImage(testimonialDto.getImage());
		testimonial.setContent(testimonialDto.getContent());

		return testimonial;
	}
}

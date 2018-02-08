package com.elvenrings.qdemo.app;

public class CarouselContainer
{
	private DefaultCarousel carousel;
	private CarouselControlBox controlBox;

	public CarouselContainer(DefaultCarousel carousel, CarouselControlBox controlBox)
	{
		this.carousel = carousel;
		this.controlBox = controlBox;
	}

	public DefaultCarousel getCarousel()
	{
		return this.carousel;
	}

	public CarouselControlBox getCarouselControlBox()
	{
		return this.controlBox;
	}

	@Override
	public String toString()
	{
		return this.carousel.toString();
	}
}

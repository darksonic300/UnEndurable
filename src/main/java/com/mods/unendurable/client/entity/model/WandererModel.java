package com.mods.unendurable.client.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class WandererModel<T extends LivingEntity> extends EntityModel<T> {

    private final ModelRenderer waist;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer hat;
    private final ModelRenderer rightArm;
    private final ModelRenderer rightItem;
    private final ModelRenderer leftArm;
    private final ModelRenderer leftItem;

    public WandererModel() {
        textureWidth = 64;
        textureHeight = 68;

        waist = new ModelRenderer(this);
        waist.setRotationPoint(0.0F, 8.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -8.0F, 0.0F);
        waist.addChild(body);
        setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
        body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 17.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, -0.0001F);
        body.addChild(head);
        setRotationAngle(head, -0.3491F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -3.9999F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        hat = new ModelRenderer(this);
        hat.setRotationPoint(0.0F, 0.0F, 0.0001F);
        head.addChild(hat);
        hat.setTextureOffset(0, 38).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 20.0F, 8.0F, 0.5F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        body.addChild(rightArm);
        setRotationAngle(rightArm, -0.48F, 0.0F, 0.0F);
        rightArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

        rightItem = new ModelRenderer(this);
        rightItem.setRotationPoint(-1.0F, 7.0F, 1.0F);
        rightArm.addChild(rightItem);


        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        body.addChild(leftArm);
        setRotationAngle(leftArm, -0.3927F, 0.0F, 0.0F);
        leftArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, true);

        leftItem = new ModelRenderer(this);
        leftItem.setRotationPoint(1.0F, 7.0F, 1.0F);
        leftArm.addChild(leftItem);

    }



    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        waist.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
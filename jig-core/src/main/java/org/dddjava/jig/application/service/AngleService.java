package org.dddjava.jig.application.service;

import org.dddjava.jig.domain.model.categories.EnumAngles;
import org.dddjava.jig.domain.model.characteristic.Characteristic;
import org.dddjava.jig.domain.model.characteristic.CharacterizedMethods;
import org.dddjava.jig.domain.model.characteristic.CharacterizedTypes;
import org.dddjava.jig.domain.model.datasources.DatasourceAngles;
import org.dddjava.jig.domain.model.decisions.DecisionAngles;
import org.dddjava.jig.domain.model.decisions.StringComparingAngle;
import org.dddjava.jig.domain.model.declaration.method.MethodDeclarations;
import org.dddjava.jig.domain.model.identifier.type.TypeIdentifiers;
import org.dddjava.jig.domain.model.implementation.ProjectData;
import org.dddjava.jig.domain.model.services.ServiceAngles;
import org.dddjava.jig.domain.model.values.ValueAngles;
import org.dddjava.jig.domain.model.values.ValueKind;
import org.springframework.stereotype.Service;

/**
 * 分析の切り口サービス
 */
@Service
public class AngleService {

    /**
     * サービスを分析する
     */
    public ServiceAngles serviceAngles(ProjectData projectData) {
        MethodDeclarations serviceMethods = projectData.characterizedMethods().serviceMethods(projectData.characterizedTypes());

        return ServiceAngles.of(serviceMethods,
                projectData.methodRelations(),
                projectData.characterizedTypes(),
                projectData.methodUsingFields());
    }

    /**
     * データソースを分析する
     */
    public DatasourceAngles datasourceAngles(ProjectData projectData) {
        CharacterizedMethods characterizedMethods = projectData.characterizedMethods();

        return DatasourceAngles.of(
                characterizedMethods.repositoryMethods(projectData.characterizedTypes()),
                characterizedMethods.mapperMethods(projectData.characterizedTypes()),
                projectData.implementationMethods(),
                projectData.methodRelations(),
                projectData.sqls());
    }

    /**
     * enumを分析する
     */
    public EnumAngles enumAngles(ProjectData projectData) {
        TypeIdentifiers enumTypeIdentifies = projectData.characterizedTypes().stream()
                .filter(Characteristic.ENUM)
                .typeIdentifiers();

        return EnumAngles.of(enumTypeIdentifies,
                projectData.characterizedTypes(),
                projectData.typeDependencies(),
                projectData.fieldDeclarations(),
                projectData.staticFieldDeclarations());
    }

    /**
     * 値を分析する
     */
    public ValueAngles valueAngles(ValueKind valueKind, ProjectData projectData) {
        return ValueAngles.of(valueKind, projectData.valueTypes(), projectData.typeDependencies());
    }

    /**
     * 文字列比較を分析する
     */
    public StringComparingAngle stringComparing(ProjectData projectData) {
        return StringComparingAngle.of(projectData.methodRelations());
    }

    /**
     * 分岐箇所を分析する
     */
    public DecisionAngles decision(ProjectData projectData) {
        MethodDeclarations methods = projectData.characterizedMethods().decisionMethods();

        CharacterizedTypes characterizedTypes = projectData.characterizedTypes();
        return DecisionAngles.of(methods, characterizedTypes);
    }
}

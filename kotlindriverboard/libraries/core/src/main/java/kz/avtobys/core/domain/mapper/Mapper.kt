package kz.avtobys.core.domain.mapper

abstract class Mapper<in FROM,out TO> {
    abstract fun map(from:FROM):TO
}